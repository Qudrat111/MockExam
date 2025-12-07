package org.example.mockexam.modules.dto.request;

import jakarta.validation.constraints.NotNull;

public class ListeningTestQuestionSubmitRequest {
    @NotNull
    private Long id;
    @NotNull
    private String answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
