package org.example.mockexam.modules.dto.response;

import org.example.mockexam.enums.WritingTestStatus;

import java.util.List;

public class WritingTestSubmitResponse {
    private Long writingTestResultId;
    private Double overallScore;
    private WritingTestStatus status;
    private List<WritingTestTaskSubmitResponse> submitTasks;

    public Double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Double overallScore) {
        this.overallScore = overallScore;
    }

    public List<WritingTestTaskSubmitResponse> getSubmitTasks() {
        return submitTasks;
    }

    public void setSubmitTasks(List<WritingTestTaskSubmitResponse> submitTasks) {
        this.submitTasks = submitTasks;
    }

    public Long getWritingTestResultId() {
        return writingTestResultId;
    }

    public void setWritingTestResultId(Long writingTestResultId) {
        this.writingTestResultId = writingTestResultId;
    }

    public WritingTestStatus getStatus() {
        return status;
    }

    public void setStatus(WritingTestStatus status) {
        this.status = status;
    }
}
