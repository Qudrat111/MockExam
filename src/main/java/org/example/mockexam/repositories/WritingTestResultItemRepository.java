package org.example.mockexam.repositories;

import org.example.mockexam.modules.entity.WritingTestResultItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WritingTestResultItemRepository extends BaseRepository<WritingTestResultItem> {
    List<WritingTestResultItem> findWritingTestResultItemByWritingTestResultIdAndDeletedFalseOrderByIdAsc(Long writingTestResultId);
}
