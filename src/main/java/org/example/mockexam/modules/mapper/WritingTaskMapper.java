package org.example.mockexam.modules.mapper;

import org.example.mockexam.modules.dto.request.WritingTaskCreateRequest;
import org.example.mockexam.modules.dto.response.WritingTaskResponse;
import org.example.mockexam.modules.dto.response.WritingTestTaskSubmitResponse;
import org.example.mockexam.modules.entity.WritingTask;
import org.example.mockexam.modules.entity.WritingTest;
import org.example.mockexam.modules.entity.WritingTestResult;
import org.example.mockexam.modules.entity.WritingTestResultItem;
import org.springframework.stereotype.Component;

@Component
public class WritingTaskMapper {

    public WritingTask toEntity(WritingTaskCreateRequest writingTaskCreateRequest, WritingTest writingTest) {
        WritingTask newWritingTask = new WritingTask();

        newWritingTask.setWritingTest(writingTest);
        newWritingTask.setTitle(writingTaskCreateRequest.getTitle());
        newWritingTask.setType(writingTaskCreateRequest.getType());
        newWritingTask.setImageUrl(writingTaskCreateRequest.getImageUrl());
        newWritingTask.setPrompt(writingTaskCreateRequest.getPrompt());
        newWritingTask.setAnswer(writingTaskCreateRequest.getAnswer());

        return newWritingTask;
    }

    public WritingTestResultItem toEntity(WritingTask writingTask, WritingTestResult writingTestResult, String userAnswer) {
        WritingTestResultItem writingTestResultItem = new WritingTestResultItem();

        writingTestResultItem.setWritingTask(writingTask);
        writingTestResultItem.setWritingTestResult(writingTestResult);
        writingTestResultItem.setUserAnswer(userAnswer);

        return writingTestResultItem;
    }

    public WritingTaskResponse toResponse(WritingTask writingTask) {
        WritingTaskResponse writingTaskResponse = new WritingTaskResponse();

        writingTaskResponse.setId(writingTask.getId());
        writingTaskResponse.setImageUrl(writingTask.getImageUrl());
        writingTaskResponse.setTitle(writingTask.getTitle());
        writingTaskResponse.setType(writingTask.getType());
        writingTaskResponse.setPrompt(writingTask.getPrompt());

        return writingTaskResponse;
    }

    public WritingTestTaskSubmitResponse toResponse(WritingTask writingTask, String userAnswer, Double score) {
        WritingTestTaskSubmitResponse writingTestTaskSubmitResponse = new WritingTestTaskSubmitResponse();

        writingTestTaskSubmitResponse.setTitle(writingTask.getTitle());
        writingTestTaskSubmitResponse.setImageUrl(writingTask.getImageUrl());
        writingTestTaskSubmitResponse.setType(writingTask.getType());
        writingTestTaskSubmitResponse.setPrompt(writingTask.getPrompt());
        writingTestTaskSubmitResponse.setScore(score);
        writingTestTaskSubmitResponse.setUserAnswer(userAnswer);

        return writingTestTaskSubmitResponse;
    }
}
