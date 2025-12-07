package org.example.mockexam.services.impl;

import org.example.mockexam.configs.exception.TestNameExistException;
import org.example.mockexam.configs.exception.UserNotFoundException;
import org.example.mockexam.configs.exception.WritingTestNotFoundException;
import org.example.mockexam.enums.WritingTestStatus;
import org.example.mockexam.modules.dto.request.WritingTestCreateRequest;
import org.example.mockexam.modules.dto.request.WritingTestReviewResultSubmitRequest;
import org.example.mockexam.modules.dto.request.WritingTestSubmitRequest;
import org.example.mockexam.modules.dto.response.*;
import org.example.mockexam.modules.entity.*;
import org.example.mockexam.modules.mapper.WritingTaskMapper;
import org.example.mockexam.modules.mapper.WritingTestMapper;
import org.example.mockexam.repositories.*;
import org.example.mockexam.services.WritingTestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static org.example.mockexam.configs.util.SecurityUtil.getUserId;

@Service
public class WritingTestServiceImpl implements WritingTestService {
    private final WritingTestRepository writingTestRepository;
    private final WritingTaskRepository writingTaskRepository;
    private final WritingTestResultRepository writingTestResultRepository;
    private final WritingTestResultItemRepository writingTestResultItemRepository;
    private final UserRepository userRepository;
    private final WritingTestMapper writingTestMapper;
    private final WritingTaskMapper writingTaskMapper;

    public WritingTestServiceImpl(WritingTestRepository writingTestRepository, WritingTaskRepository writingTaskRepository, WritingTestResultRepository writingTestResultRepository, WritingTestResultItemRepository writingTestResultItemRepository, UserRepository userRepository, WritingTestMapper writingTestMapper, WritingTaskMapper writingTaskMapper) {
        this.writingTestRepository = writingTestRepository;
        this.writingTaskRepository = writingTaskRepository;
        this.writingTestResultRepository = writingTestResultRepository;
        this.writingTestResultItemRepository = writingTestResultItemRepository;
        this.userRepository = userRepository;
        this.writingTestMapper = writingTestMapper;
        this.writingTaskMapper = writingTaskMapper;
    }

    @Transactional
    @Override
    public void add(WritingTestCreateRequest writingTestCreateRequest) {
        if (writingTestRepository.existsByName(writingTestCreateRequest.getName()))
            throw new TestNameExistException();

        WritingTest newWritingTest = writingTestRepository.save(writingTestMapper.toEntity(writingTestCreateRequest));

        writingTestCreateRequest.getTasks().forEach(writingTask ->
                writingTaskRepository.save(writingTaskMapper.toEntity(writingTask, newWritingTest))
        );
    }

    @Transactional
    @Override
    public WritingTestSubmitResponse submit(WritingTestSubmitRequest writingTestSubmitRequest) {
        WritingTest writingTest = writingTestRepository.findByIdAndDeletedFalse(writingTestSubmitRequest.getId()).orElseThrow(WritingTestNotFoundException::new);

        if (!writingTest.getActive()) throw new WritingTestNotFoundException();

        User user = userRepository.findByIdAndDeletedFalse(getUserId()).orElseThrow(UserNotFoundException::new);
        List<WritingTestTaskSubmitResponse> submitTaskResponses = new ArrayList<>();
        WritingTestResult writingTestResult = writingTestResultRepository.save(writingTestMapper.toEntity(writingTest, user));


        writingTestSubmitRequest.getSubmitTasks().forEach(writingSubmitTask ->
                {
                    WritingTask writingTask = writingTaskRepository.findByIdAndDeletedFalse(writingSubmitTask.getId()).orElseThrow();
                    WritingTestResultItem writingTestResultItem = writingTestResultItemRepository.save(writingTaskMapper
                            .toEntity(writingTask, writingTestResult, writingSubmitTask.getUserAnswer())
                    );

                    submitTaskResponses.add(writingTaskMapper
                            .toResponse(writingTask, writingTestResultItem.getUserAnswer(), writingTestResultItem.getScore()));

                }
        );

        return writingTestMapper.toResponse(writingTestResult.getId(), writingTestResult.getOverallScore(), writingTestResult.getStatus(), submitTaskResponses);
    }

    @Override
    public Map<Long, WritingTestSubmitResponse> getSubmissionsForReview() {
        Map<Long, WritingTestSubmitResponse> writingTestResultSubmitResponses = new HashMap<>();

        writingTestResultRepository.findWritingTestResultByStatusAndDeletedFalse(WritingTestStatus.NOT_REVIEWED).forEach(writingTestResult -> {
                    List<WritingTestTaskSubmitResponse> writingTestTaskSubmitResponses = new ArrayList<>();

                    writingTestResultItemRepository.findWritingTestResultItemByWritingTestResultIdAndDeletedFalseOrderByIdAsc(writingTestResult.getId()).forEach(writingTestResultItem ->
                            writingTestTaskSubmitResponses.add(writingTaskMapper.toResponse(
                                            writingTestResultItem.getWritingTask(),
                                            writingTestResultItem.getUserAnswer(),
                                            writingTestResultItem.getScore()
                                    )
                            )
                    );

                    writingTestResultSubmitResponses.put(writingTestResult.getUser().getId(), writingTestMapper.toResponse(
                                    writingTestResult.getId(),
                                    writingTestResult.getOverallScore(),
                                    writingTestResult.getStatus(),
                                    writingTestTaskSubmitResponses
                            )
                    );
                }
        );

        return writingTestResultSubmitResponses;
    }

    @Override
    public void submitReviewResult(WritingTestReviewResultSubmitRequest writingTestReviewResultSubmitRequest) {
        WritingTestResult writingTestResult = writingTestResultRepository
                .findByIdAndUserIdAndDeletedFalse(
                        writingTestReviewResultSubmitRequest.getWritingTestResultId(),
                        writingTestReviewResultSubmitRequest.getUserId(),
                        WritingTestStatus.NOT_REVIEWED
                )
                .orElseThrow();
        AtomicReference<Double> overallScore = new AtomicReference<>(0.0);

        writingTestResultItemRepository.findWritingTestResultItemByWritingTestResultIdAndDeletedFalseOrderByIdAsc(writingTestResult.getId()).forEach(writingTestResultItem -> {

                    writingTestReviewResultSubmitRequest.getReviewTaskResults().forEach(writingTestTaskReviewResultSubmitRequest -> {
                                if (writingTestResultItem.getWritingTask().getType() == writingTestTaskReviewResultSubmitRequest.getType()) {
                                    writingTestResultItem.setScore(writingTestTaskReviewResultSubmitRequest.getScore());
                                    overallScore.getAndUpdate(v -> v + writingTestTaskReviewResultSubmitRequest.getScore());
                                }
                            }
                    );

                    writingTestResultItemRepository.save(writingTestResultItem);
                }
        );

        writingTestResult.setStatus(WritingTestStatus.REVIEWED);
        writingTestResult.setOverallScore(calculateOverallScore(overallScore.get()));

        writingTestResultRepository.save(writingTestResult);
    }

    @Override
    public TestResponse changeActive(Long id) {
        WritingTest existWritingTest = writingTestRepository.findByIdAndDeletedFalse(id).orElseThrow(WritingTestNotFoundException::new);

        existWritingTest.setActive(!existWritingTest.getActive());

        return writingTestMapper.toResponse(writingTestRepository.save(existWritingTest));
    }

    @Override
    public List<ActiveWritingTestResponse> getActives() {
        List<ActiveWritingTestResponse> activeWritingTestResponseList = new ArrayList<>();

        writingTestRepository
                .findWritingTestByActiveAndDeletedFalse(true)
                .forEach(writingTest -> {
                            List<WritingTaskResponse> writingTaskResponseList = writingTaskRepository
                                    .findWritingTaskByWritingTestIdAndDeletedFalseOrderByIdAsc(writingTest.getId())
                                    .stream()
                                    .map(writingTaskMapper::toResponse)
                                    .toList();

                            activeWritingTestResponseList.add(
                                    writingTestMapper
                                            .toResponse(writingTest, writingTaskResponseList)
                            );
                        }
                );

        return activeWritingTestResponseList;
    }

    @Override
    public List<TestResponse> getAll() {
        return writingTestRepository
                .findAllNotDeleted()
                .stream()
                .map(writingTestMapper::toResponse)
                .toList();
    }

    private Double calculateOverallScore(Double overallScore) {
        double calculatedOverallScore = 0.0;

        if (overallScore % 2 == 1 || overallScore % 2 == 0) {
            calculatedOverallScore = overallScore / 2.0;
        } else {
            calculatedOverallScore = (double) Math.round(overallScore / 2.0);
        }

        return calculatedOverallScore;
    }
}
