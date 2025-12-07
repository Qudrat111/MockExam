package org.example.mockexam.modules.mapper;

import org.example.mockexam.modules.dto.request.ListeningTestCreateRequest;
import org.example.mockexam.modules.dto.response.*;
import org.example.mockexam.modules.entity.ListeningTest;
import org.example.mockexam.modules.entity.ListeningTestResult;
import org.example.mockexam.modules.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListeningTestMapper {

    public ListeningTest toEntity(ListeningTestCreateRequest listeningTestCreateRequest) {
        ListeningTest newListeningTest = new ListeningTest();

        newListeningTest.setName(listeningTestCreateRequest.getName());
        newListeningTest.setTimeLimit(listeningTestCreateRequest.getTimeLimit());
        newListeningTest.setActiveDate(listeningTestCreateRequest.getActiveDate());

        return newListeningTest;
    }

    public ListeningTestResult toEntity(ListeningTest listeningTest, User user, Integer correctAnswer, Integer wrongAnswer) {
        ListeningTestResult listeningTestResult = new ListeningTestResult();

        listeningTestResult.setUser(user);
        listeningTestResult.setListeningTest(listeningTest);
        listeningTestResult.setCorrectAnswer(correctAnswer);
        listeningTestResult.setWrongAnswer(wrongAnswer);

        return listeningTestResult;
    }

    public TestResponse toResponse(ListeningTest listeningTest) {
        TestResponse newTestResponse = new TestResponse();

        newTestResponse.setId(listeningTest.getId());
        newTestResponse.setName(listeningTest.getName());
        newTestResponse.setTimeLimit(listeningTest.getTimeLimit());
        newTestResponse.setActiveDate(listeningTest.getActiveDate());
        newTestResponse.setActive(listeningTest.getActive());
        newTestResponse.setType(listeningTest.getType());

        return newTestResponse;
    }

    public ActiveListeningTestResponse toResponse(ListeningTest listeningTest, List<ListeningPartResponse> parts) {
        ActiveListeningTestResponse activeListeningTestResponse = new ActiveListeningTestResponse();

        activeListeningTestResponse.setId(listeningTest.getId());
        activeListeningTestResponse.setName(listeningTest.getName());
        activeListeningTestResponse.setTimeLimit(listeningTest.getTimeLimit());
        activeListeningTestResponse.setActiveDate(listeningTest.getActiveDate());
        activeListeningTestResponse.setType(listeningTest.getType());
        activeListeningTestResponse.setActive(listeningTest.getActive());
        activeListeningTestResponse.setParts(parts);

        return activeListeningTestResponse;
    }

    public ListeningTestSubmitResponse toResponse(int correctAnswer, int wrongAnswer, List<ListeningTestPartSubmitResponse> listeningTestPartSubmitResponses) {
        ListeningTestSubmitResponse listeningTestSubmitResponse = new ListeningTestSubmitResponse();

        listeningTestSubmitResponse.setCorrectAnswer(correctAnswer);
        listeningTestSubmitResponse.setWrongAnswer(wrongAnswer);
        listeningTestSubmitResponse.setSubmitParts(listeningTestPartSubmitResponses);

        return listeningTestSubmitResponse;
    }
}
