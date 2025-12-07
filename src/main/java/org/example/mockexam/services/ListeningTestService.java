package org.example.mockexam.services;

import org.example.mockexam.modules.dto.request.ListeningTestCreateRequest;
import org.example.mockexam.modules.dto.request.ListeningTestSubmitRequest;
import org.example.mockexam.modules.dto.response.ActiveListeningTestResponse;
import org.example.mockexam.modules.dto.response.ListeningTestSubmitResponse;
import org.example.mockexam.modules.dto.response.TestResponse;

import java.util.List;

public interface ListeningTestService {
    void add(ListeningTestCreateRequest listeningTestCreateRequest);

    ListeningTestSubmitResponse submit(ListeningTestSubmitRequest listeningTestSubmitRequest);

    TestResponse changeActive(Long id);

    List<ActiveListeningTestResponse> getActives();

    List<TestResponse> getAll();
}
