package org.example.mockexam.controllers;

import jakarta.validation.Valid;
import org.example.mockexam.configs.annotation.IsAdmin;
import org.example.mockexam.modules.dto.request.ListeningTestCreateRequest;
import org.example.mockexam.modules.dto.request.ListeningTestSubmitRequest;
import org.example.mockexam.modules.dto.response.ActiveListeningTestResponse;
import org.example.mockexam.modules.dto.response.ListeningTestSubmitResponse;
import org.example.mockexam.modules.dto.response.TestResponse;
import org.example.mockexam.services.ListeningTestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/listening")
public class  ListeningTestController {
    private final ListeningTestService listeningTestService;

    public ListeningTestController(ListeningTestService listeningTestService) {
        this.listeningTestService = listeningTestService;
    }

    @GetMapping("user")
    public List<ActiveListeningTestResponse> getActives() {
        return listeningTestService.getActives();
    }

    @PostMapping("submit")
    public ListeningTestSubmitResponse submit(@Valid @RequestBody ListeningTestSubmitRequest listeningTestSubmitRequest) {
        return listeningTestService.submit(listeningTestSubmitRequest);
    }

    @IsAdmin
    @PostMapping
    public void add(@RequestBody @Valid ListeningTestCreateRequest listeningCreateTestRequest) {
        listeningTestService.add(listeningCreateTestRequest);
    }

    @IsAdmin
    @PutMapping("change-active/{id}")
    public TestResponse changeActive(@PathVariable Long id) {
        return listeningTestService.changeActive(id);
    }

    @IsAdmin
    @GetMapping
    public List<TestResponse> getAll() {
        return listeningTestService.getAll();
    }
}