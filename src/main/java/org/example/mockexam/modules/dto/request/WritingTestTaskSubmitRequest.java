package org.example.mockexam.modules.dto.request;

import jakarta.validation.constraints.NotNull;

public class WritingTestTaskSubmitRequest {
    @NotNull
    private Long id;
    @NotNull
    private String userAnswer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}
