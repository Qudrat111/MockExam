package org.example.mockexam.modules.dto.response;

import java.util.List;

public class ReadingTestPassageSubmitResponse {
    private String title;
    private String content;
    private List<ReadingTestQuestionSubmitResponse> submitQuestions;

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

    public List<ReadingTestQuestionSubmitResponse> getSubmitQuestions() {
        return submitQuestions;
    }

    public void setSubmitQuestions(List<ReadingTestQuestionSubmitResponse> submitQuestions) {
        this.submitQuestions = submitQuestions;
    }
}
