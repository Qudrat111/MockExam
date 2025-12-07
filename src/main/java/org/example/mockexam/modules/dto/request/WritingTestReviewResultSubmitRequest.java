package org.example.mockexam.modules.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class WritingTestReviewResultSubmitRequest {
    @NotNull
    private Long userId;
    @NotNull
    private Long writingTestResultId;
    @Valid
    @NotEmpty
    @Size(min = 2, max = 2)
    private List<WritingTestTaskReviewResultSubmitRequest> reviewTaskResults;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWritingTestResultId() {
        return writingTestResultId;
    }

    public void setWritingTestResultId(Long writingTestResultId) {
        this.writingTestResultId = writingTestResultId;
    }

    public List<WritingTestTaskReviewResultSubmitRequest> getReviewTaskResults() {
        return reviewTaskResults;
    }

    public void setReviewTaskResults(List<WritingTestTaskReviewResultSubmitRequest> reviewTaskResults) {
        this.reviewTaskResults = reviewTaskResults;
    }
}
