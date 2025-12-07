package org.example.mockexam.modules.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
public class ReadingTestResultItem extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    ReadingQuestion readingQuestion;
    @ManyToOne(fetch = FetchType.LAZY)
    ReadingTestResult readingTestResult;
    @Column(nullable = false)
    private String userAnswer;
    @Column(nullable = false)
    private Boolean isCorrectAnswer;

    public ReadingQuestion getReadingQuestion() {
        return readingQuestion;
    }

    public void setReadingQuestion(ReadingQuestion readingQuestion) {
        this.readingQuestion = readingQuestion;
    }

    public ReadingTestResult getReadingTestResult() {
        return readingTestResult;
    }

    public void setReadingTestResult(ReadingTestResult readingTestResult) {
        this.readingTestResult = readingTestResult;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public Boolean getIsCorrectAnswer() {
        return isCorrectAnswer;
    }

    public void setIsCorrectAnswer(Boolean correctAnswer) {
        isCorrectAnswer = correctAnswer;
    }
}
