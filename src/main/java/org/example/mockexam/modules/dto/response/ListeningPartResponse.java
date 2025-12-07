package org.example.mockexam.modules.dto.response;

import java.util.List;

public class ListeningPartResponse {
    private Long id;
    private String title;
    private String audioUrl;
    private String imageUrl;
    private List<ListeningQuestionResponse> questions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<ListeningQuestionResponse> getQuestions() {
        return questions;
    }

    public void setQuestions(List<ListeningQuestionResponse> questions) {
        this.questions = questions;
    }
}
