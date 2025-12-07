package org.example.mockexam.modules.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
public class ListeningTestResult extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private ListeningTest listeningTest;
    @Column(nullable = false)
    private Integer correctAnswer;
    @Column(nullable = false)
    private Integer wrongAnswer;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public ListeningTest getListeningTest() {
        return listeningTest;
    }

    public void setListeningTest(ListeningTest listeningTest) {
        this.listeningTest = listeningTest;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
