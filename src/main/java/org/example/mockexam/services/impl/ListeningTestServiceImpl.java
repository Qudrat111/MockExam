package org.example.mockexam.services.impl;

import org.example.mockexam.configs.exception.ListeningTestInactiveException;
import org.example.mockexam.configs.exception.ListeningTestNotFoundException;
import org.example.mockexam.configs.exception.TestNameExistException;
import org.example.mockexam.configs.exception.UserNotFoundException;
import org.example.mockexam.modules.dto.request.ListeningTestCreateRequest;
import org.example.mockexam.modules.dto.request.ListeningTestSubmitRequest;
import org.example.mockexam.modules.dto.response.*;
import org.example.mockexam.modules.entity.*;
import org.example.mockexam.modules.mapper.ListeningPartMapper;
import org.example.mockexam.modules.mapper.ListeningQuestionMapper;
import org.example.mockexam.modules.mapper.ListeningTestMapper;
import org.example.mockexam.repositories.*;
import org.example.mockexam.services.ListeningTestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.example.mockexam.configs.util.SecurityUtil.getUserId;

@Service
public class ListeningTestServiceImpl implements ListeningTestService {
    private final ListeningTestRepository listeningTestRepository;
    private final ListeningPartRepository listeningPartRepository;
    private final ListeningQuestionRepository listeningQuestionRepository;
    private final UserRepository userRepository;
    private final ListeningTestResultRepository listeningTestResultRepository;
    private final ListeningTestResultItemRepository listeningTestResultItemRepository;
    private final ListeningTestMapper listeningTestMapper;
    private final ListeningPartMapper listeningPartMapper;
    private final ListeningQuestionMapper listeningQuestionMapper;

    public ListeningTestServiceImpl(ListeningTestRepository listeningTestRepository, ListeningPartRepository listeningPartRepository, ListeningQuestionRepository listeningQuestionRepository, UserRepository userRepository, ListeningTestResultRepository listeningTestResultRepository, ListeningTestResultItemRepository listeningTestResultItemRepository, ListeningTestMapper listeningTestMapper, ListeningPartMapper listeningPartMapper, ListeningQuestionMapper listeningQuestionMapper) {
        this.listeningTestRepository = listeningTestRepository;
        this.listeningPartRepository = listeningPartRepository;
        this.listeningQuestionRepository = listeningQuestionRepository;
        this.userRepository = userRepository;
        this.listeningTestResultRepository = listeningTestResultRepository;
        this.listeningTestResultItemRepository = listeningTestResultItemRepository;
        this.listeningTestMapper = listeningTestMapper;
        this.listeningPartMapper = listeningPartMapper;
        this.listeningQuestionMapper = listeningQuestionMapper;
    }

    @Transactional
    @Override
    public void add(ListeningTestCreateRequest listeningTestCreateRequest) {
        if (listeningTestRepository.existsByName(listeningTestCreateRequest.getName()))
            throw new TestNameExistException();

        ListeningTest newListeningTest = listeningTestRepository.save(listeningTestMapper.toEntity(listeningTestCreateRequest));

        listeningTestCreateRequest.getParts().forEach(listeningPart ->
                {
                    ListeningPart newListeningPart = listeningPartRepository.save(listeningPartMapper.toEntity(listeningPart, newListeningTest));

                    listeningPart.getQuestions().forEach(listeningQuestion ->
                            listeningQuestionRepository.save(listeningQuestionMapper.toEntity(listeningQuestion, newListeningPart))
                    );
                }
        );
    }

    @Transactional
    @Override
    public ListeningTestSubmitResponse submit(ListeningTestSubmitRequest listeningTestSubmitRequest) {
        ListeningTest listeningTest = listeningTestRepository.findByIdAndDeletedFalse(listeningTestSubmitRequest.getId()).orElseThrow(ListeningTestNotFoundException::new);

        if (!listeningTest.getActive()) throw new ListeningTestInactiveException();

        User user = userRepository.findByIdAndDeletedFalse(getUserId()).orElseThrow(UserNotFoundException::new);
        List<ListeningTestResultItem> listeningTestResultItems = new ArrayList<>();
        List<ListeningTestPartSubmitResponse> submitPartResponses = new ArrayList<>();
        AtomicInteger correctAnswer = new AtomicInteger();
        AtomicInteger wrongAnswer = new AtomicInteger();


        listeningTestSubmitRequest.getSubmitParts().forEach(listeningSubmitPart ->
                {
                    ListeningPart listeningPart = listeningPartRepository.findByIdAndDeletedFalse(listeningSubmitPart.getId()).orElseThrow();
                    List<ListeningTestQuestionSubmitResponse> listeningTestQuestionSubmitResponses = new ArrayList<>();

                    listeningSubmitPart.getSubmitQuestions().forEach(listeningSubmitQuestion ->
                            {
                                ListeningQuestion listeningQuestion = listeningQuestionRepository.findByIdAndDeletedFalse(listeningSubmitQuestion.getId()).orElseThrow();
                                ListeningTestResultItem listeningTestResultItem = new ListeningTestResultItem();

                                listeningTestResultItem.setUserAnswer(listeningSubmitQuestion.getAnswer());
                                listeningTestResultItem.setListeningQuestion(listeningQuestion);

                                if (listeningQuestion.getAnswer().equals(listeningSubmitQuestion.getAnswer())) {
                                    correctAnswer.getAndIncrement();
                                    listeningTestResultItem.setIsCorrectAnswer(true);
                                } else {
                                    wrongAnswer.getAndIncrement();
                                    listeningTestResultItem.setIsCorrectAnswer(false);
                                }

                                listeningTestResultItems.add(listeningTestResultItem);
                                listeningTestQuestionSubmitResponses.add(listeningQuestionMapper.toResponse(listeningQuestion, listeningTestResultItem.getIsCorrectAnswer(), listeningTestResultItem.getUserAnswer()));
                            }
                    );

                    submitPartResponses.add(listeningPartMapper.toResponse(listeningPart.getTitle(), listeningPart.getAudioUrl(), listeningPart.getImageUrl(), listeningTestQuestionSubmitResponses));
                }
        );

        ListeningTestResult listeningTestResult = listeningTestMapper.toEntity(listeningTest, user, correctAnswer.get(), wrongAnswer.get());

        listeningTestResultItems.forEach(readingTestResultItem ->
                readingTestResultItem.setListeningTestResult(listeningTestResult)
        );

        listeningTestResultRepository.save(listeningTestResult);
        listeningTestResultItemRepository.saveAll(listeningTestResultItems);

        return listeningTestMapper.toResponse(correctAnswer.get(), wrongAnswer.get(), submitPartResponses);
    }

    @Override
    public TestResponse changeActive(Long id) {
        ListeningTest existListeningTest = listeningTestRepository.findByIdAndDeletedFalse(id).orElseThrow(ListeningTestNotFoundException::new);

        existListeningTest.setActive(!existListeningTest.getActive());

        return listeningTestMapper.toResponse(listeningTestRepository.save(existListeningTest));
    }

    @Override
    public List<ActiveListeningTestResponse> getActives() {
        List<ActiveListeningTestResponse> activeListeningTestResponseList = new ArrayList<>();

        listeningTestRepository
                .findListeningTestByActiveAndDeletedFalse(true)
                .forEach(listeningTest -> {
                            List<ListeningPartResponse> listeingPartResponseList = new ArrayList<>();

                            listeningPartRepository
                                    .findListeningPartByListeningTestIdAndDeletedFalseOrderByIdAsc(listeningTest.getId())
                                    .forEach(listeningPart -> {
                                                List<ListeningQuestionResponse> listeningQuestionResponseList = listeningQuestionRepository
                                                        .findListeningQuestionByPartIdAndDeletedFalseOrderByIdAsc(listeningPart.getId())
                                                        .stream()
                                                        .map(listeningQuestionMapper::toResponse)
                                                        .toList();

                                                listeingPartResponseList.add(
                                                        listeningPartMapper
                                                                .toResponse(listeningPart, listeningQuestionResponseList)
                                                );
                                            }
                                    );

                            activeListeningTestResponseList.add(
                                    listeningTestMapper
                                            .toResponse(listeningTest, listeingPartResponseList)
                            );
                        }
                );

        return activeListeningTestResponseList;
    }

    @Override
    public List<TestResponse> getAll() {
        return listeningTestRepository
                .findAllNotDeleted()
                .stream()
                .map(listeningTestMapper::toResponse)
                .toList();
    }
}
