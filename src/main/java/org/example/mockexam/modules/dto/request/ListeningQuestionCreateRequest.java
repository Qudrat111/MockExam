package org.example.mockexam.modules.dto.request;

import jakarta.validation.constraints.NotNull;
import org.example.mockexam.enums.ListeningQuestionType;

import java.util.List;

public class ListeningQuestionCreateRequest {
    @NotNull
    private ListeningQuestionType type;
    @NotNull
    private String text;
    @NotNull
    private String answer;
    private List<String> answerOptions;

    public @NotNull ListeningQuestionType getType() {
        return type;
    }

    public void setType(@NotNull ListeningQuestionType type) {
        this.type = type;
    }

    public @NotNull String getText() {
        return text;
    }

    public void setText(@NotNull String text) {
        this.text = text;
    }

    public @NotNull String getAnswer() {
        return answer;
    }

    public void setAnswer(@NotNull String answer) {
        this.answer = answer;
    }

    public List<String> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(List<String> answerOptions) {
        this.answerOptions = answerOptions;
    }
}
