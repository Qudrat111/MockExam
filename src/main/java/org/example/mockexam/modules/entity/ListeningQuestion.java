package org.example.mockexam.modules.entity;

import jakarta.persistence.*;
import org.example.mockexam.enums.ListeningQuestionType;

import java.util.List;

@Entity
public class ListeningQuestion extends BaseEntity {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ListeningQuestionType type;
    @Column(nullable = false)
    private String text;
    @Column(nullable = false)
    private String answer;
    @ElementCollection
    private List<String> answerOptions;
    @ManyToOne(fetch = FetchType.LAZY)
    private ListeningPart part;

    public ListeningQuestionType getType() {
        return type;
    }

    public void setType(ListeningQuestionType type) {
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

    public ListeningPart getPart() {
        return part;
    }

    public void setPart(ListeningPart part) {
        this.part = part;
    }
}
