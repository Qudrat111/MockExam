package org.example.mockexam.modules.mapper;

import org.example.mockexam.enums.WritingTestStatus;
import org.example.mockexam.modules.dto.request.WritingTestCreateRequest;
import org.example.mockexam.modules.dto.response.*;
import org.example.mockexam.modules.entity.User;
import org.example.mockexam.modules.entity.WritingTest;
import org.example.mockexam.modules.entity.WritingTestResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WritingTestMapper {

    public WritingTest toEntity(WritingTestCreateRequest writingTestCreateRequest) {
        WritingTest newWritingTest = new WritingTest();

        newWritingTest.setName(writingTestCreateRequest.getName());
        newWritingTest.setTimeLimit(writingTestCreateRequest.getTimeLimit());
        newWritingTest.setActiveDate(writingTestCreateRequest.getActiveDate());

        return newWritingTest;
    }

    public WritingTestResult toEntity(WritingTest writingTest, User user) {
        WritingTestResult readingTestResult = new WritingTestResult();

        readingTestResult.setUser(user);
        readingTestResult.setWritingTest(writingTest);

        return readingTestResult;
    }

    public TestResponse toResponse(WritingTest writingTest) {
        TestResponse newTestResponse = new TestResponse();

        newTestResponse.setName(writingTest.getName());
        newTestResponse.setId(writingTest.getId());
        newTestResponse.setTimeLimit(writingTest.getTimeLimit());
        newTestResponse.setActiveDate(writingTest.getActiveDate());
        newTestResponse.setActive(writingTest.getActive());
        newTestResponse.setType(writingTest.getType());

        return newTestResponse;
    }

    public ActiveWritingTestResponse toResponse(WritingTest writingTest, List<WritingTaskResponse> tasks) {
        ActiveWritingTestResponse activeWritingTestResponse = new ActiveWritingTestResponse();

        activeWritingTestResponse.setId(writingTest.getId());
        activeWritingTestResponse.setName(writingTest.getName());
        activeWritingTestResponse.setTimeLimit(writingTest.getTimeLimit());
        activeWritingTestResponse.setActiveDate(writingTest.getActiveDate());
        activeWritingTestResponse.setType(writingTest.getType());
        activeWritingTestResponse.setActive(writingTest.getActive());
        activeWritingTestResponse.setTasks(tasks);

        return activeWritingTestResponse;
    }

    public WritingTestSubmitResponse toResponse(Long id, Double overallScore, WritingTestStatus status, List<WritingTestTaskSubmitResponse> writingTestTaskSubmitResponses) {
        WritingTestSubmitResponse writingTestSubmitResponse = new WritingTestSubmitResponse();

        writingTestSubmitResponse.setWritingTestResultId(id);
        writingTestSubmitResponse.setOverallScore(overallScore);
        writingTestSubmitResponse.setStatus(status);
        writingTestSubmitResponse.setSubmitTasks(writingTestTaskSubmitResponses);

        return writingTestSubmitResponse;
    }
}
