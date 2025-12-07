package org.example.mockexam.repositories;

import org.example.mockexam.enums.WritingTestStatus;
import org.example.mockexam.modules.entity.WritingTestResult;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WritingTestResultRepository extends BaseRepository<WritingTestResult> {

    List<WritingTestResult> findWritingTestResultByUserIdAndDeletedFalse(Long userId);

    List<WritingTestResult> findWritingTestResultByStatusAndDeletedFalse(WritingTestStatus status);

    @Query("""
             select wtr from WritingTestResult  wtr
             where
             wtr.deleted = false
             and wtr.id = :id
             and wtr.user.id = :userId
             and wtr.status = :status
            """)
    Optional<WritingTestResult> findByIdAndUserIdAndDeletedFalse(Long id, Long userId, WritingTestStatus status);
}
