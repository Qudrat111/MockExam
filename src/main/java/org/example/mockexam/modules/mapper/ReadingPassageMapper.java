package org.example.mockexam.modules.mapper;

import org.example.mockexam.modules.dto.request.ReadingPassageCreateRequest;
import org.example.mockexam.modules.dto.response.ReadingPassageResponse;
import org.example.mockexam.modules.dto.response.ReadingQuestionResponse;
import org.example.mockexam.modules.dto.response.ReadingTestPassageSubmitResponse;
import org.example.mockexam.modules.dto.response.ReadingTestQuestionSubmitResponse;
import org.example.mockexam.modules.entity.ReadingPassage;
import org.example.mockexam.modules.entity.ReadingTest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReadingPassageMapper {

    public ReadingPassage toEntity(ReadingPassageCreateRequest readingPassageCreateRequest, ReadingTest readingTest) {
        ReadingPassage newReadingPassage = new ReadingPassage();

        newReadingPassage.setReadingTest(readingTest);
        newReadingPassage.setTitle(readingPassageCreateRequest.getTitle());
        newReadingPassage.setContent(readingPassageCreateRequest.getContent());

        return newReadingPassage;
    }

    public ReadingPassageResponse toResponse(ReadingPassage readingPassage, List<ReadingQuestionResponse> readingQuestionResponse) {
        ReadingPassageResponse readingPassageResponse = new ReadingPassageResponse();

        readingPassageResponse.setId(readingPassage.getId());
        readingPassageResponse.setTitle(readingPassage.getTitle());
        readingPassageResponse.setContent(readingPassage.getContent());
        readingPassageResponse.setQuestions(readingQuestionResponse);

        return readingPassageResponse;
    }

    public ReadingTestPassageSubmitResponse toResponse(String title, String content, List<ReadingTestQuestionSubmitResponse> readingTestQuestionSubmitResponses) {
        ReadingTestPassageSubmitResponse readingTestPassageSubmitResponse = new ReadingTestPassageSubmitResponse();

        readingTestPassageSubmitResponse.setTitle(title);
        readingTestPassageSubmitResponse.setContent(content);
        readingTestPassageSubmitResponse.setSubmitQuestions(readingTestQuestionSubmitResponses);

        return readingTestPassageSubmitResponse;
    }
}
