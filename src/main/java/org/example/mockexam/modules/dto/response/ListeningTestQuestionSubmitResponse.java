package org.example.mockexam.modules.dto.response;

import org.example.mockexam.enums.ListeningQuestionType;

import java.util.List;

public class ListeningTestQuestionSubmitResponse {
    private ListeningQuestionType type;
    private String text;
    private List<String> answerOptions;
    private String userAnswer;
    private Boolean isCorrectAnswer;

    public ListeningQuestionType getType() {
        return type;
    }

    public void setType(ListeningQuestionType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(List<String> answerOptions) {
        this.answerOptions = answerOptions;
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
