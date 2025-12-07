package org.example.mockexam.modules.dto.response;

import org.example.mockexam.enums.ReadingQuestionType;

import java.util.List;

public class ReadingQuestionResponse {
    private Long id;
    private ReadingQuestionType type;
    private String text;
    private List<String> answerOptions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
