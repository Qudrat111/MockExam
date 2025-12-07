package org.example.mockexam.repositories;

import org.example.mockexam.modules.entity.WritingTask;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WritingTaskRepository extends BaseRepository<WritingTask> {

    List<WritingTask> findWritingTaskByWritingTestIdAndDeletedFalseOrderByIdAsc(Long id);
}
