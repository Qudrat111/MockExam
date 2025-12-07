package org.example.mockexam.modules.entity;

import jakarta.persistence.*;
import org.example.mockexam.enums.WritingTaskType;

@Entity
public class WritingTask extends BaseEntity {
    @Column(nullable = false)
    private String imageUrl;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WritingTaskType type;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String prompt;
    @Column(columnDefinition = "TEXT")
    private String answer;
    @ManyToOne(fetch = FetchType.LAZY)
    private WritingTest writingTest;

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public WritingTest getWritingTest() {
        return writingTest;
    }

    public void setWritingTest(WritingTest writingTest) {
        this.writingTest = writingTest;
    }
}
