package org.example.mockexam.modules.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
public class WritingTestResultItem extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    WritingTask writingTask;
    @ManyToOne(fetch = FetchType.LAZY)
    WritingTestResult writingTestResult;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String userAnswer;
    private Double score;

    public WritingTask getWritingTask() {
        return writingTask;
    }

    public void setWritingTask(WritingTask writingTask) {
        this.writingTask = writingTask;
    }

    public WritingTestResult getWritingTestResult() {
        return writingTestResult;
    }

    public void setWritingTestResult(WritingTestResult writingTestResult) {
        this.writingTestResult = writingTestResult;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
