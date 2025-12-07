package org.example.mockexam.modules.dto.response;

import org.example.mockexam.enums.WritingTaskType;

public class WritingTestTaskSubmitResponse {
    private String imageUrl;
    private String title;
    private WritingTaskType type;
    private String prompt;
    private String userAnswer;
    private Double score;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public WritingTaskType getType() {
        return type;
    }

    public void setType(WritingTaskType type) {
        this.type = type;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
