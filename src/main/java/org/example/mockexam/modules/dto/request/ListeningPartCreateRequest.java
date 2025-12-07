package org.example.mockexam.modules.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class ListeningPartCreateRequest {
    @Valid
    @NotEmpty
    @Size(min = 10, max = 10)
    List<ListeningQuestionCreateRequest> questions;
    @NotNull
    private String title;
    @NotNull
    private String audioUrl;
    private String imageUrl;

    public @NotNull String getTitle() {
        return title;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    public @NotNull String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(@NotNull String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public @NotEmpty List<ListeningQuestionCreateRequest> getQuestions() {
        return questions;
    }

    public void setQuestions(@NotEmpty List<ListeningQuestionCreateRequest> questions) {
        this.questions = questions;
    }
}
