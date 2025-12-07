package org.example.mockexam.repositories;

import org.example.mockexam.modules.entity.ListeningQuestion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListeningQuestionRepository extends BaseRepository<ListeningQuestion> {

    List<ListeningQuestion> findListeningQuestionByPartIdAndDeletedFalseOrderByIdAsc(Long id);
}
