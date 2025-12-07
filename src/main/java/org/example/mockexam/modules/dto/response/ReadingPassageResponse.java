package org.example.mockexam.modules.dto.response;

import java.util.List;

public class ReadingPassageResponse {
    private Long id;
    private String title;
    private String content;
    private List<ReadingQuestionResponse> questions;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ReadingQuestionResponse> getQuestions() {
        return questions;
    }

    public void setQuestions(List<ReadingQuestionResponse> questions) {
        this.questions = questions;
    }
}
