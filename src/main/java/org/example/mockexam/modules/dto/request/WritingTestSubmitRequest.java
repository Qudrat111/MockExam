package org.example.mockexam.modules.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class WritingTestSubmitRequest {
    @NotNull
    Long id;
    @Valid
    @NotEmpty
    @Size(min = 2, max = 2)
    private List<WritingTestTaskSubmitRequest> submitTasks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<WritingTestTaskSubmitRequest> getSubmitTasks() {
        return submitTasks;
    }

    public void setSubmitTasks(List<WritingTestTaskSubmitRequest> submitTasks) {
        this.submitTasks = submitTasks;
    }
}
