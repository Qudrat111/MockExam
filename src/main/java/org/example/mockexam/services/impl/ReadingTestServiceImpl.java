package org.example.mockexam.services.impl;

import org.example.mockexam.configs.exception.ReadingTestInactiveException;
import org.example.mockexam.configs.exception.ReadingTestNotFoundException;
import org.example.mockexam.configs.exception.TestNameExistException;
import org.example.mockexam.configs.exception.UserNotFoundException;
import org.example.mockexam.modules.dto.request.ReadingTestCreateRequest;
import org.example.mockexam.modules.dto.request.ReadingTestSubmitRequest;
import org.example.mockexam.modules.dto.response.*;
import org.example.mockexam.modules.entity.*;
import org.example.mockexam.modules.mapper.ReadingPassageMapper;
import org.example.mockexam.modules.mapper.ReadingQuestionMapper;
import org.example.mockexam.modules.mapper.ReadingTestMapper;
import org.example.mockexam.repositories.*;
import org.example.mockexam.services.ReadingTestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.example.mockexam.configs.util.SecurityUtil.getUserId;

@Service
public class ReadingTestServiceImpl implements ReadingTestService {
    private final ReadingTestRepository readingTestRepository;
    private final ReadingPassageRepository readingPassageRepository;
    private final ReadingQuestionRepository readingQuestionRepository;
    private final ReadingTestMapper readingTestMapper;
    private final ReadingPassageMapper readingPassageMapper;
    private final ReadingQuestionMapper readingQuestionMapper;
    private final UserRepository userRepository;
    private final ReadingTestResultRepository readingTestResultRepository;
    private final ReadingTestResultItemRepository readingTestResultItemRepository;

    public ReadingTestServiceImpl(ReadingTestRepository readingTestRepository, ReadingPassageRepository readingPassageRepository, ReadingQuestionRepository readingQuestionRepository, ReadingTestMapper readingTestMapper, ReadingPassageMapper readingPassageMapper, ReadingQuestionMapper readingQuestionMapper, UserRepository userRepository, ReadingTestResultRepository readingTestResultRepository, ReadingTestResultItemRepository readingTestResultItemRepository) {
        this.readingTestRepository = readingTestRepository;
        this.readingPassageRepository = readingPassageRepository;
        this.readingQuestionRepository = readingQuestionRepository;
        this.readingTestMapper = readingTestMapper;
        this.readingPassageMapper = readingPassageMapper;
        this.readingQuestionMapper = readingQuestionMapper;
        this.userRepository = userRepository;
        this.readingTestResultRepository = readingTestResultRepository;
        this.readingTestResultItemRepository = readingTestResultItemRepository;
    }

    @Transactional
    @Override
    public void add(ReadingTestCreateRequest readingTestCreateRequest) {
        if (readingTestRepository.existsByName(readingTestCreateRequest.getName())) throw new TestNameExistException();

        ReadingTest newReadingTest = readingTestRepository.save(readingTestMapper.toEntity(readingTestCreateRequest));

        readingTestCreateRequest.getPassages().forEach(readingPassage ->
                {
                    ReadingPassage newReadingPassage = readingPassageRepository.save(readingPassageMapper.toEntity(readingPassage, newReadingTest));

                    readingPassage.getQuestions().forEach(readingQuestion ->
                            readingQuestionRepository.save(readingQuestionMapper.toEntity(readingQuestion, newReadingPassage))
                    );
                }
        );
    }

    @Transactional
    @Override
    public ReadingTestSubmitResponse submit(ReadingTestSubmitRequest readingTestSubmitRequest) {
        ReadingTest readingTest = readingTestRepository.findByIdAndDeletedFalse(readingTestSubmitRequest.getId()).orElseThrow(ReadingTestNotFoundException::new);

        if (!readingTest.getActive()) throw new ReadingTestInactiveException();

        User user = userRepository.findByIdAndDeletedFalse(getUserId()).orElseThrow(UserNotFoundException::new);
        List<ReadingTestResultItem> readingTestResultItems = new ArrayList<>();
        List<ReadingTestPassageSubmitResponse> submitPassageResponses = new ArrayList<>();
        AtomicInteger correctAnswer = new AtomicInteger();
        AtomicInteger wrongAnswer = new AtomicInteger();


        readingTestSubmitRequest.getSubmitPassages().forEach(readingSubmitPassage ->
                {
                    ReadingPassage readingPassage = readingPassageRepository.findByIdAndDeletedFalse(readingSubmitPassage.getId()).orElseThrow();
                    List<ReadingTestQuestionSubmitResponse> readingTestQuestionSubmitResponses = new ArrayList<>();

                    readingSubmitPassage.getSubmitQuestions().forEach(readingSubmitQuestion ->
                            {
                                ReadingQuestion readingQuestion = readingQuestionRepository.findByIdAndDeletedFalse(readingSubmitQuestion.getId()).orElseThrow();
                                ReadingTestResultItem readingTestResultItem = new ReadingTestResultItem();

                                readingTestResultItem.setUserAnswer(readingSubmitQuestion.getAnswer());
                                readingTestResultItem.setReadingQuestion(readingQuestion);

                                if (readingQuestion.getAnswer().equals(readingSubmitQuestion.getAnswer())) {
                                    readingTestResultItem.setIsCorrectAnswer(true);
                                    correctAnswer.getAndIncrement();
                                } else {
                                    readingTestResultItem.setIsCorrectAnswer(false);
                                    wrongAnswer.getAndIncrement();
                                }

                                readingTestResultItems.add(readingTestResultItem);
                                readingTestQuestionSubmitResponses.add(readingQuestionMapper.toResponse(readingQuestion, readingTestResultItem.getIsCorrectAnswer(), readingTestResultItem.getUserAnswer()));
                            }
                    );

                    submitPassageResponses.add(readingPassageMapper.toResponse(readingPassage.getTitle(), readingPassage.getContent(), readingTestQuestionSubmitResponses));
                }
        );

        ReadingTestResult readingTestResult = readingTestMapper.toEntity(readingTest, user, correctAnswer.get(), wrongAnswer.get());

        readingTestResultItems.forEach(readingTestResultItem ->
                readingTestResultItem.setReadingTestResult(readingTestResult)
        );

        readingTestResultRepository.save(readingTestResult);
        readingTestResultItemRepository.saveAll(readingTestResultItems);

        return readingTestMapper.toResponse(correctAnswer.get(), wrongAnswer.get(), submitPassageResponses);
    }

    @Override
    public TestResponse changeActive(Long id) {
        ReadingTest existReadingTest = readingTestRepository.findByIdAndDeletedFalse(id).orElseThrow(ReadingTestNotFoundException::new);

        existReadingTest.setActive(!existReadingTest.getActive());

        return readingTestMapper.toResponse(readingTestRepository.save(existReadingTest));
    }

    @Override
    public List<ActiveReadingTestResponse> getActives() {
        List<ActiveReadingTestResponse> activeReadingTestResponseList = new ArrayList<>();

        readingTestRepository
                .findReadingTestByActiveAndDeletedFalse(true)
                .forEach(readingTest -> {
                            List<ReadingPassageResponse> readingPassageResponseList = new ArrayList<>();

                            readingPassageRepository
                                    .findReadingPassageByReadingTestIdAndDeletedFalseOrderByIdAsc(readingTest.getId())
                                    .forEach(readingPassage -> {
                                                List<ReadingQuestionResponse> readingQuestionResponseList = readingQuestionRepository
                                                        .findReadingQuestionByPassageIdAndDeletedFalseOrderByIdAsc(readingPassage.getId())
                                                        .stream()
                                                        .map(readingQuestionMapper::toResponse)
                                                        .toList();

                                                readingPassageResponseList.add(
                                                        readingPassageMapper
                                                                .toResponse(readingPassage, readingQuestionResponseList)
                                                );
                                            }
                                    );

                            activeReadingTestResponseList.add(
                                    readingTestMapper
                                            .toResponse(readingTest, readingPassageResponseList)
                            );
                        }
                );

        return activeReadingTestResponseList;
    }

    @Override
    public List<TestResponse> getAll() {
        return readingTestRepository
                .findAllNotDeleted()
                .stream()
                .map(readingTestMapper::toResponse)
                .toList();
    }
}
