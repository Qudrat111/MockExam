package org.example.mockexam.modules.dto.response;

import java.util.List;

public class ListeningTestSubmitResponse {
    private Integer correctAnswer;
    private Integer wrongAnswer;
    private List<ListeningTestPartSubmitResponse> submitParts;

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

    public List<ListeningTestPartSubmitResponse> getSubmitParts() {
        return submitParts;
    }

    public void setSubmitParts(List<ListeningTestPartSubmitResponse> submitParts) {
        this.submitParts = submitParts;
    }
}
