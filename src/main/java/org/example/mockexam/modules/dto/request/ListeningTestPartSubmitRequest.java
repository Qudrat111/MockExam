package org.example.mockexam.modules.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class ListeningTestPartSubmitRequest {
    @NotNull
    private Long id;
    @Valid
    @NotEmpty
    @Size(min = 10, max = 10)
    private List<ListeningTestQuestionSubmitRequest> submitQuestions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ListeningTestQuestionSubmitRequest> getSubmitQuestions() {
        return submitQuestions;
    }

    public void setSubmitQuestions(List<ListeningTestQuestionSubmitRequest> submitQuestions) {
        this.submitQuestions = submitQuestions;
    }
}
