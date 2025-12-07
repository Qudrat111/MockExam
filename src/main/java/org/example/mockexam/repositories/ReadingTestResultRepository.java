package org.example.mockexam.repositories;

import org.example.mockexam.modules.entity.ReadingTestResult;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingTestResultRepository extends BaseRepository<ReadingTestResult> {

    List<ReadingTestResult> findReadingTestResultByUserIdAndDeletedFalse(Long userId);
}
