package org.example.mockexam.modules.entity;

import jakarta.persistence.*;
import org.example.mockexam.enums.WritingTestStatus;

@Entity
public class WritingTestResult extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    WritingTest writingTest;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private Double overallScore;
    @Enumerated(EnumType.STRING)
    private WritingTestStatus status = WritingTestStatus.NOT_REVIEWED;

    public WritingTest getWritingTest() {
        return writingTest;
    }

    public void setWritingTest(WritingTest writingTest) {
        this.writingTest = writingTest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Double overallScore) {
        this.overallScore = overallScore;
    }

    public WritingTestStatus getStatus() {
        return status;
    }

    public void setStatus(WritingTestStatus status) {
        this.status = status;
    }
}
