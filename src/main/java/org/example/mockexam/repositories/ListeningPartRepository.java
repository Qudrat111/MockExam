package org.example.mockexam.repositories;

import org.example.mockexam.modules.entity.ListeningPart;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListeningPartRepository extends BaseRepository<ListeningPart> {

    List<ListeningPart> findListeningPartByListeningTestIdAndDeletedFalseOrderByIdAsc(Long id);
}
