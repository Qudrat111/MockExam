package org.example.mockexam.modules.mapper;

import org.example.mockexam.modules.dto.request.ReadingTestCreateRequest;
import org.example.mockexam.modules.dto.response.*;
import org.example.mockexam.modules.entity.ReadingTest;
import org.example.mockexam.modules.entity.ReadingTestResult;
import org.example.mockexam.modules.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReadingTestMapper {
    public ReadingTest toEntity(ReadingTestCreateRequest readingTestCreateRequest) {
        ReadingTest newReadingTest = new ReadingTest();

        newReadingTest.setName(readingTestCreateRequest.getName());
        newReadingTest.setTimeLimit(readingTestCreateRequest.getTimeLimit());
        newReadingTest.setActiveDate(readingTestCreateRequest.getActiveDate());

        return newReadingTest;
    }

    public ReadingTestResult toEntity(ReadingTest readingTest, User user, Integer correctAnswer, Integer wrongAnswer) {
        ReadingTestResult readingTestResult = new ReadingTestResult();

        readingTestResult.setUser(user);
        readingTestResult.setReadingTest(readingTest);
        readingTestResult.setCorrectAnswer(correctAnswer);
        readingTestResult.setWrongAnswer(wrongAnswer);

        return readingTestResult;
    }

    public TestResponse toResponse(ReadingTest readingTest) {
        TestResponse newTestResponse = new TestResponse();

        newTestResponse.setId(readingTest.getId());
        newTestResponse.setName(readingTest.getName());
        newTestResponse.setTimeLimit(readingTest.getTimeLimit());
        newTestResponse.setActiveDate(readingTest.getActiveDate());
        newTestResponse.setType(readingTest.getType());
        newTestResponse.setActive(readingTest.getActive());

        return newTestResponse;
    }

    public ActiveReadingTestResponse toResponse(ReadingTest readingTest, List<ReadingPassageResponse> passages) {
        ActiveReadingTestResponse activeReadingTestResponse = new ActiveReadingTestResponse();

        activeReadingTestResponse.setId(readingTest.getId());
        activeReadingTestResponse.setName(readingTest.getName());
        activeReadingTestResponse.setTimeLimit(readingTest.getTimeLimit());
        activeReadingTestResponse.setActiveDate(readingTest.getActiveDate());
        activeReadingTestResponse.setType(readingTest.getType());
        activeReadingTestResponse.setActive(readingTest.getActive());
        activeReadingTestResponse.setPassages(passages);

        return activeReadingTestResponse;
    }

    public ReadingTestSubmitResponse toResponse(int correctAnswer, int wrongAnswer, List<ReadingTestPassageSubmitResponse> readingTestPassageSubmitResponses) {
        ReadingTestSubmitResponse readingTestSubmitResponse = new ReadingTestSubmitResponse();

        readingTestSubmitResponse.setCorrectAnswer(correctAnswer);
        readingTestSubmitResponse.setWrongAnswer(wrongAnswer);
        readingTestSubmitResponse.setSubmitPassages(readingTestPassageSubmitResponses);

        return readingTestSubmitResponse;
    }
}
