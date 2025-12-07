package org.example.mockexam.modules.dto.request;

import jakarta.validation.constraints.NotNull;
import org.example.mockexam.enums.WritingTaskType;

public class WritingTestTaskReviewResultSubmitRequest {
    @NotNull
    private WritingTaskType type;
    @NotNull
    private Double score;

    public WritingTaskType getType() {
        return type;
    }

    public void setType(WritingTaskType type) {
        this.type = type;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
