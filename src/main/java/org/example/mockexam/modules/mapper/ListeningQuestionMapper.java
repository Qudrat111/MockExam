package org.example.mockexam.modules.mapper;

import org.example.mockexam.modules.dto.request.ListeningQuestionCreateRequest;
import org.example.mockexam.modules.dto.response.ListeningQuestionResponse;
import org.example.mockexam.modules.dto.response.ListeningTestQuestionSubmitResponse;
import org.example.mockexam.modules.entity.ListeningPart;
import org.example.mockexam.modules.entity.ListeningQuestion;
import org.springframework.stereotype.Component;

@Component
public class ListeningQuestionMapper {

    public ListeningQuestion toEntity(ListeningQuestionCreateRequest listeningQuestionCreateRequest, ListeningPart listeningPart) {
        ListeningQuestion newListeningQuestion = new ListeningQuestion();

        newListeningQuestion.setPart(listeningPart);
        newListeningQuestion.setText(listeningQuestionCreateRequest.getText());
        newListeningQuestion.setType(listeningQuestionCreateRequest.getType());
        newListeningQuestion.setAnswer(listeningQuestionCreateRequest.getAnswer());
        newListeningQuestion.setAnswerOptions(listeningQuestionCreateRequest.getAnswerOptions());

        return newListeningQuestion;
    }

    public ListeningQuestionResponse toResponse(ListeningQuestion listeningQuestion) {
        ListeningQuestionResponse listeningQuestionResponse = new ListeningQuestionResponse();

        listeningQuestionResponse.setId(listeningQuestion.getId());
        listeningQuestionResponse.setText(listeningQuestion.getText());
        listeningQuestionResponse.setType(listeningQuestion.getType());
        listeningQuestionResponse.setAnswerOptions(listeningQuestion.getAnswerOptions());

        return listeningQuestionResponse;
    }

    public ListeningTestQuestionSubmitResponse toResponse(ListeningQuestion listeningQuestion, Boolean isCorrectAnswer, String userAnswer) {
        ListeningTestQuestionSubmitResponse listeningTestQuestionSubmitResponse = new ListeningTestQuestionSubmitResponse();

        listeningTestQuestionSubmitResponse.setIsCorrectAnswer(isCorrectAnswer);
        listeningTestQuestionSubmitResponse.setUserAnswer(userAnswer);
        listeningTestQuestionSubmitResponse.setText(listeningQuestion.getText());
        listeningTestQuestionSubmitResponse.setType(listeningQuestion.getType());
        listeningTestQuestionSubmitResponse.setAnswerOptions(listeningQuestion.getAnswerOptions());

        return listeningTestQuestionSubmitResponse;
    }
}
