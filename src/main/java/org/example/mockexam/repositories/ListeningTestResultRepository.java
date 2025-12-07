package org.example.mockexam.repositories;

import org.example.mockexam.modules.entity.ListeningTestResult;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListeningTestResultRepository extends BaseRepository<ListeningTestResult> {

    List<ListeningTestResult> findListeningTestResultByUserIdAndDeletedFalse(Long userId);
}
