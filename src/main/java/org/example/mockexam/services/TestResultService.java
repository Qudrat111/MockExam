package org.example.mockexam.services;

import org.example.mockexam.modules.dto.response.ListeningTestSubmitResponse;
import org.example.mockexam.modules.dto.response.ReadingTestSubmitResponse;
import org.example.mockexam.modules.dto.response.TestResultResponse;
import org.example.mockexam.modules.dto.response.WritingTestSubmitResponse;

import java.util.List;

public interface TestResultService {
    List<TestResultResponse> getAll();

    List<ListeningTestSubmitResponse> getUserListeningTestResults(Long userId);

    List<ReadingTestSubmitResponse> getUserReadingTestResults(Long userId);

    List<WritingTestSubmitResponse> getUserWritingTestResults(Long userId);
}
