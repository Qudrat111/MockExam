package org.example.mockexam.modules.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ReadingPassageCreateRequest {
    @Valid
    @NotEmpty
    List<ReadingQuestionCreateRequest> questions;
    @NotNull
    private String title;
    @NotNull
    private String content;

    public @NotEmpty List<ReadingQuestionCreateRequest> getQuestions() {
        return questions;
    }

    public void setQuestions(@NotEmpty List<ReadingQuestionCreateRequest> questions) {
        this.questions = questions;
    }

    public @NotNull String getTitle() {
        return title;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    public @NotNull String getContent() {
        return content;
    }

    public void setContent(@NotNull String content) {
        this.content = content;
    }
}
