package org.example.mockexam.modules.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
public class ReadingTestResult extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    ReadingTest readingTest;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @Column(nullable = false) Integer correctAnswer;
    @Column(nullable = false) Integer wrongAnswer;

    public ReadingTest getReadingTest() {
        return readingTest;
    }

    public void setReadingTest(ReadingTest readingTest) {
        this.readingTest = readingTest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Integer getWrongAnswer() {
        return wrongAnswer;
    }

    public void setWrongAnswer(Integer wrongAnswer) {
        this.wrongAnswer = wrongAnswer;
    }
}
