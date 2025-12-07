package org.example.mockexam.modules.dto.response;

import java.util.List;

public class ListeningTestPartSubmitResponse {
    private String title;
    private String audioUrl;
    private String imageUrl;
    private List<ListeningTestQuestionSubmitResponse> submitQuestions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<ListeningTestQuestionSubmitResponse> getSubmitQuestions() {
        return submitQuestions;
    }

    public void setSubmitQuestions(List<ListeningTestQuestionSubmitResponse> submitQuestions) {
        this.submitQuestions = submitQuestions;
    }
}
