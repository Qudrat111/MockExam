package org.example.mockexam.modules.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ReadingTestPassageSubmitRequest {
    @NotNull
    private Long id;
    @Valid
    @NotEmpty
    private List<ReadingTestQuestionSubmitRequest> submitQuestions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ReadingTestQuestionSubmitRequest> getSubmitQuestions() {
        return submitQuestions;
    }

    public void setSubmitQuestions(List<ReadingTestQuestionSubmitRequest> submitQuestions) {
        this.submitQuestions = submitQuestions;
    }
}
