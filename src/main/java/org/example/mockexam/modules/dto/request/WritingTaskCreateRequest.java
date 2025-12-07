package org.example.mockexam.modules.dto.request;

import jakarta.validation.constraints.NotNull;
import org.example.mockexam.enums.WritingTaskType;

public class WritingTaskCreateRequest {
    @NotNull
    String imageUrl;
    @NotNull
    private String title;
    @NotNull
    private WritingTaskType type;
    @NotNull
    private String prompt;
    private String answer;

    public @NotNull String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@NotNull String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public @NotNull String getTitle() {
        return title;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    public @NotNull WritingTaskType getType() {
        return type;
    }

    public void setType(@NotNull WritingTaskType type) {
        this.type = type;
    }

    public @NotNull String getPrompt() {
        return prompt;
    }

    public void setPrompt(@NotNull String prompt) {
        this.prompt = prompt;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
