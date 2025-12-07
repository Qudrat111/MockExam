package org.example.mockexam.controllers;

import org.example.mockexam.configs.annotation.IsAdmin;
import org.example.mockexam.modules.dto.response.ListeningTestSubmitResponse;
import org.example.mockexam.modules.dto.response.ReadingTestSubmitResponse;
import org.example.mockexam.modules.dto.response.TestResultResponse;
import org.example.mockexam.modules.dto.response.WritingTestSubmitResponse;
import org.example.mockexam.services.TestResultService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.example.mockexam.configs.util.SecurityUtil.getUserId;

@RestController
@RequestMapping("api/v1/test-result")
public class TestResultController {
    private final TestResultService testResultService;

    public TestResultController(TestResultService testResultService) {
        this.testResultService = testResultService;
    }

    @IsAdmin
    @GetMapping
    List<TestResultResponse> getAll() {
        return testResultService.getAll();
    }

    @GetMapping("user/listening")
    List<ListeningTestSubmitResponse> getUserListeningTestResults() {
        return testResultService.getUserListeningTestResults(getUserId());
    }

    @GetMapping("user/reading")
    List<ReadingTestSubmitResponse> getUserReadingTestResults() {
        return testResultService.getUserReadingTestResults(getUserId());
    }

    @GetMapping("user/writing")
    List<WritingTestSubmitResponse> getUserWritingTestResults() {
        return testResultService.getUserWritingTestResults(getUserId());
    }
}
