package org.example.mockexam.modules.dto.response;

public class TestResultResponse {
    private String username;
    private Integer listeningCorrectAnswer;
    private Integer listeningWrongAnswer;
    private Integer readingCorrectAnswer;
    private Integer readingWrongAnswer;
    private Double writingScore;
    private String submittedDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getListeningCorrectAnswer() {
        return listeningCorrectAnswer;
    }

    public void setListeningCorrectAnswer(Integer listeningCorrectAnswer) {
        this.listeningCorrectAnswer = listeningCorrectAnswer;
    }

    public Integer getListeningWrongAnswer() {
        return listeningWrongAnswer;
    }

    public void setListeningWrongAnswer(Integer listeningWrongAnswer) {
        this.listeningWrongAnswer = listeningWrongAnswer;
    }

    public Integer getReadingCorrectAnswer() {
        return readingCorrectAnswer;
    }

    public void setReadingCorrectAnswer(Integer readingCorrectAnswer) {
        this.readingCorrectAnswer = readingCorrectAnswer;
    }

    public Integer getReadingWrongAnswer() {
        return readingWrongAnswer;
    }

    public void setReadingWrongAnswer(Integer readingWrongAnswer) {
        this.readingWrongAnswer = readingWrongAnswer;
    }

    public Double getWritingScore() {
        return writingScore;
    }

    public void setWritingScore(Double writingScore) {
        this.writingScore = writingScore;
    }

    public String getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(String submittedDate) {
        this.submittedDate = submittedDate;
    }
}
