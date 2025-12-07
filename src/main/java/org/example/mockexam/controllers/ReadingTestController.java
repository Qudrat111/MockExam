package org.example.mockexam.controllers;

import jakarta.validation.Valid;
import org.example.mockexam.configs.annotation.IsAdmin;
import org.example.mockexam.modules.dto.request.ReadingTestCreateRequest;
import org.example.mockexam.modules.dto.request.ReadingTestSubmitRequest;
import org.example.mockexam.modules.dto.response.ActiveReadingTestResponse;
import org.example.mockexam.modules.dto.response.ReadingTestSubmitResponse;
import org.example.mockexam.modules.dto.response.TestResponse;
import org.example.mockexam.services.ReadingTestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/reading")
public class ReadingTestController {
    private final ReadingTestService readingTestService;

    public ReadingTestController(ReadingTestService readingTestService) {
        this.readingTestService = readingTestService;
    }

    @GetMapping("user")
    public List<ActiveReadingTestResponse> getActives() {
        return readingTestService.getActives();
    }

    @PostMapping("submit")
    public ReadingTestSubmitResponse submit(@Valid @RequestBody ReadingTestSubmitRequest readingTestSubmitRequest) {
        return readingTestService.submit(readingTestSubmitRequest);
    }

    @IsAdmin
    @PostMapping
    public void add(@RequestBody @Valid ReadingTestCreateRequest readingTestCreateRequest) {
        readingTestService.add(readingTestCreateRequest);
    }

    @IsAdmin
    @PutMapping("change-active/{id}")
    public TestResponse changeActive(@PathVariable Long id) {
        return readingTestService.changeActive(id);
    }

    @IsAdmin
    @GetMapping
    public List<TestResponse> getAll() {
        return readingTestService.getAll();
    }
}
