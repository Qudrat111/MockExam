package org.example.mockexam.modules.dto.response;

import org.example.mockexam.enums.WritingTaskType;

public class WritingTaskResponse {
    private Long id;
    String imageUrl;
    private String title;
    private WritingTaskType type;
    private String prompt;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}

