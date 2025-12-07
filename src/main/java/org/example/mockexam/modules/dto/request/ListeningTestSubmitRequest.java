package org.example.mockexam.modules.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class ListeningTestSubmitRequest {
    @NotNull
    private Long id;
    @Valid
    @NotEmpty
    @Size(min = 4, max = 4)
    private List<ListeningTestPartSubmitRequest> submitParts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ListeningTestPartSubmitRequest> getSubmitParts() {
        return submitParts;
    }

    public void setSubmitParts(List<ListeningTestPartSubmitRequest> submitParts) {
        this.submitParts = submitParts;
    }
}
