package org.example.mockexam.modules.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class ReadingTestSubmitRequest {
    @NotNull
    private Long id;
    @Valid
    @NotEmpty
    @Size(min = 3, max = 3)
    private List<ReadingTestPassageSubmitRequest> submitPassages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ReadingTestPassageSubmitRequest> getSubmitPassages() {
        return submitPassages;
    }

    public void setSubmitPassages(List<ReadingTestPassageSubmitRequest> submitPassages) {
        this.submitPassages = submitPassages;
    }
}
