package org.example.mockexam.modules.dto.response;

import java.util.List;

public class ReadingTestSubmitResponse {
    private Integer correctAnswer;
    private Integer wrongAnswer;
    private List<ReadingTestPassageSubmitResponse> submitPassages;

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

    public List<ReadingTestPassageSubmitResponse> getSubmitPassages() {
        return submitPassages;
    }

    public void setSubmitPassages(List<ReadingTestPassageSubmitResponse> submitPassages) {
        this.submitPassages = submitPassages;
    }
}
