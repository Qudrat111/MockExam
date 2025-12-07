package org.example.mockexam.modules.entity;

import jakarta.persistence.*;
import org.example.mockexam.enums.ReadingQuestionType;

import java.util.List;

@Entity
public class ReadingQuestion extends BaseEntity {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReadingQuestionType type;
    @Column(nullable = false)
    private String text;
    @Column(nullable = false)
    private String answer;
    @ElementCollection
    private List<String> answerOptions;
    @ManyToOne(fetch = FetchType.LAZY)
    private ReadingPassage passage;

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<String> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(List<String> answerOptions) {
        this.answerOptions = answerOptions;
    }

    public ReadingPassage getPassage() {
        return passage;
    }

    public void setPassage(ReadingPassage passage) {
        this.passage = passage;
    }
}
