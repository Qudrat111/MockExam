package org.example.mockexam.repositories;

import org.example.mockexam.modules.entity.ReadingQuestion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingQuestionRepository extends BaseRepository<ReadingQuestion> {

    List<ReadingQuestion> findReadingQuestionByPassageIdAndDeletedFalseOrderByIdAsc(Long id);
}
