package org.example.mockexam.controllers;

import jakarta.validation.Valid;
import org.example.mockexam.configs.annotation.IsAdmin;
import org.example.mockexam.modules.dto.request.WritingTestCreateRequest;
import org.example.mockexam.modules.dto.request.WritingTestReviewResultSubmitRequest;
import org.example.mockexam.modules.dto.request.WritingTestSubmitRequest;
import org.example.mockexam.modules.dto.response.ActiveWritingTestResponse;
import org.example.mockexam.modules.dto.response.TestResponse;
import org.example.mockexam.modules.dto.response.WritingTestSubmitResponse;
import org.example.mockexam.services.WritingTestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/writing")
public class WritingTestController {
    private final WritingTestService writingTestService;

    public WritingTestController(WritingTestService writingTestService) {
        this.writingTestService = writingTestService;
    }

    @GetMapping("user")
    public List<ActiveWritingTestResponse> getActives() {
        return writingTestService.getActives();
    }

    @PostMapping("submit")
    public WritingTestSubmitResponse submit(@Valid @RequestBody WritingTestSubmitRequest writingTestSubmitRequest) {
        return writingTestService.submit(writingTestSubmitRequest);
    }

    @IsAdmin
    @GetMapping("review")
    public Map<Long, WritingTestSubmitResponse> getSubmissionsForReview() {
        return writingTestService.getSubmissionsForReview();
    }

    @IsAdmin
    @PostMapping("review")
    void submitReviewResult(@RequestBody @Valid WritingTestReviewResultSubmitRequest writingTestReviewResultSubmitRequest) {
        writingTestService.submitReviewResult(writingTestReviewResultSubmitRequest);
    }

    @IsAdmin
    @PostMapping
    public void add(@RequestBody @Valid WritingTestCreateRequest writingTestCreateRequest) {
        writingTestService.add(writingTestCreateRequest);
    }

    @IsAdmin
    @PutMapping("change-active/{id}")
    public TestResponse changeActive(@PathVariable Long id) {
        return writingTestService.changeActive(id);
    }

    @IsAdmin
    @GetMapping
    public List<TestResponse> getAll() {
        return writingTestService.getAll();
    }
}
