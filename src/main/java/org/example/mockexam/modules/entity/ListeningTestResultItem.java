package org.example.mockexam.modules.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
public class ListeningTestResultItem extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    ListeningQuestion listeningQuestion;
    @ManyToOne(fetch = FetchType.LAZY)
    ListeningTestResult listeningTestResult;
    @Column(nullable = false)
    private String userAnswer;
    @Column(nullable = false)
    private Boolean isCorrectAnswer;

    public ListeningQuestion getListeningQuestion() {
        return listeningQuestion;
    }

    public void setListeningQuestion(ListeningQuestion listeningQuestion) {
        this.listeningQuestion = listeningQuestion;
    }

    public ListeningTestResult getListeningTestResult() {
        return listeningTestResult;
    }

    public void setListeningTestResult(ListeningTestResult listeningTestResult) {
        this.listeningTestResult = listeningTestResult;
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
