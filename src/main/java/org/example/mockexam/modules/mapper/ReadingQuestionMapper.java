package org.example.mockexam.modules.mapper;

import org.example.mockexam.modules.dto.request.ReadingQuestionCreateRequest;
import org.example.mockexam.modules.dto.response.ReadingQuestionResponse;
import org.example.mockexam.modules.dto.response.ReadingTestQuestionSubmitResponse;
import org.example.mockexam.modules.entity.ReadingPassage;
import org.example.mockexam.modules.entity.ReadingQuestion;
import org.springframework.stereotype.Component;

@Component
public class ReadingQuestionMapper {

    public ReadingQuestion toEntity(ReadingQuestionCreateRequest readingQuestionCreateRequest, ReadingPassage readingPassage) {
        ReadingQuestion newReadingQuestion = new ReadingQuestion();

        newReadingQuestion.setPassage(readingPassage);
        newReadingQuestion.setType(readingQuestionCreateRequest.getType());
        newReadingQuestion.setText(readingQuestionCreateRequest.getText());
        newReadingQuestion.setAnswer(readingQuestionCreateRequest.getAnswer());
        newReadingQuestion.setAnswerOptions(readingQuestionCreateRequest.getAnswerOptions());

        return newReadingQuestion;
    }

    public ReadingQuestionResponse toResponse(ReadingQuestion readingQuestion) {
        ReadingQuestionResponse readingQuestionResponse = new ReadingQuestionResponse();

        readingQuestionResponse.setId(readingQuestion.getId());
        readingQuestionResponse.setText(readingQuestion.getText());
        readingQuestionResponse.setType(readingQuestion.getType());
        readingQuestionResponse.setAnswerOptions(readingQuestion.getAnswerOptions());

        return readingQuestionResponse;
    }

    public ReadingTestQuestionSubmitResponse toResponse(ReadingQuestion readingQuestion, Boolean isCorrectAnswer, String userAnswer) {
        ReadingTestQuestionSubmitResponse readingTestQuestionSubmitResponse = new ReadingTestQuestionSubmitResponse();

        readingTestQuestionSubmitResponse.setIsCorrectAnswer(isCorrectAnswer);
        readingTestQuestionSubmitResponse.setUserAnswer(userAnswer);
        readingTestQuestionSubmitResponse.setText(readingQuestion.getText());
        readingTestQuestionSubmitResponse.setType(readingQuestion.getType());
        readingTestQuestionSubmitResponse.setAnswerOptions(readingQuestion.getAnswerOptions());

        return readingTestQuestionSubmitResponse;
    }
}
