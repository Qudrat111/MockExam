package org.example.mockexam.services;

import org.example.mockexam.modules.dto.request.ReadingTestCreateRequest;
import org.example.mockexam.modules.dto.request.ReadingTestSubmitRequest;
import org.example.mockexam.modules.dto.response.ActiveReadingTestResponse;
import org.example.mockexam.modules.dto.response.ReadingTestSubmitResponse;
import org.example.mockexam.modules.dto.response.TestResponse;

import java.util.List;

public interface ReadingTestService {
    void add(ReadingTestCreateRequest readingTestCreateRequest);

    ReadingTestSubmitResponse submit(ReadingTestSubmitRequest readingTestSubmitRequest);

    TestResponse changeActive(Long id);

    List<ActiveReadingTestResponse> getActives();

    List<TestResponse> getAll();
}
