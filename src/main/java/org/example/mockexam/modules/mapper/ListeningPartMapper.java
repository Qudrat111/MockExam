package org.example.mockexam.modules.mapper;

import org.example.mockexam.modules.dto.request.ListeningPartCreateRequest;
import org.example.mockexam.modules.dto.response.ListeningPartResponse;
import org.example.mockexam.modules.dto.response.ListeningQuestionResponse;
import org.example.mockexam.modules.dto.response.ListeningTestPartSubmitResponse;
import org.example.mockexam.modules.dto.response.ListeningTestQuestionSubmitResponse;
import org.example.mockexam.modules.entity.ListeningPart;
import org.example.mockexam.modules.entity.ListeningTest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListeningPartMapper {

    public ListeningPart toEntity(ListeningPartCreateRequest listeningPartCreateRequest, ListeningTest listeningTest) {
        ListeningPart newlisteningPart = new ListeningPart();

        newlisteningPart.setListeningTest(listeningTest);
        newlisteningPart.setTitle(listeningPartCreateRequest.getTitle());
        newlisteningPart.setAudioUrl(listeningPartCreateRequest.getAudioUrl());
        newlisteningPart.setImageUrl(listeningPartCreateRequest.getImageUrl());

        return newlisteningPart;
    }

    public ListeningPartResponse toResponse(ListeningPart listeningPart, List<ListeningQuestionResponse> listeningQuestionResponse) {
        ListeningPartResponse listeningPartResponse = new ListeningPartResponse();

        listeningPartResponse.setId(listeningPart.getId());
        listeningPartResponse.setTitle(listeningPart.getTitle());
        listeningPartResponse.setAudioUrl(listeningPart.getAudioUrl());
        listeningPartResponse.setImageUrl(listeningPart.getImageUrl());
        listeningPartResponse.setQuestions(listeningQuestionResponse);

        return listeningPartResponse;
    }

    public ListeningTestPartSubmitResponse toResponse(String title, String audioUrl, String imageUrl, List<ListeningTestQuestionSubmitResponse> listeningTestQuestionSubmitResponses) {
        ListeningTestPartSubmitResponse listeningTestPartSubmitResponse = new ListeningTestPartSubmitResponse();

        listeningTestPartSubmitResponse.setTitle(title);
        listeningTestPartSubmitResponse.setAudioUrl(audioUrl);
        listeningTestPartSubmitResponse.setImageUrl(imageUrl);
        listeningTestPartSubmitResponse.setSubmitQuestions(listeningTestQuestionSubmitResponses);

        return listeningTestPartSubmitResponse;
    }
}
