package org.example.mockexam.modules.dto.response;

import org.example.mockexam.enums.ReadingQuestionType;

import java.util.List;

public class ReadingTestQuestionSubmitResponse {
    private ReadingQuestionType type;
    private String text;
    private List<String> answerOptions;
    private String userAnswer;
    private Boolean isCorrectAnswer;

    public ReadingQuestionType getType() {
        return type;
    }

    public void setType(ReadingQuestionType type) {
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
