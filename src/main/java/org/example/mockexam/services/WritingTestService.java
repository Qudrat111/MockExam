package org.example.mockexam.services;

import org.example.mockexam.modules.dto.request.WritingTestCreateRequest;
import org.example.mockexam.modules.dto.request.WritingTestReviewResultSubmitRequest;
import org.example.mockexam.modules.dto.request.WritingTestSubmitRequest;
import org.example.mockexam.modules.dto.response.ActiveWritingTestResponse;
import org.example.mockexam.modules.dto.response.TestResponse;
import org.example.mockexam.modules.dto.response.WritingTestSubmitResponse;

import java.util.List;
import java.util.Map;

public interface WritingTestService {
    void add(WritingTestCreateRequest writingTestCreateRequest);

    WritingTestSubmitResponse submit(WritingTestSubmitRequest writingTestSubmitRequest);

    Map<Long, WritingTestSubmitResponse> getSubmissionsForReview();

    void submitReviewResult(WritingTestReviewResultSubmitRequest writingTestReviewResultSubmitRequest);

    TestResponse changeActive(Long id);

    List<ActiveWritingTestResponse> getActives();

    List<TestResponse> getAll();
}
