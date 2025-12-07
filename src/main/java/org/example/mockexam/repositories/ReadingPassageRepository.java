package org.example.mockexam.repositories;

import org.example.mockexam.modules.entity.ReadingPassage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingPassageRepository extends BaseRepository<ReadingPassage> {

    List<ReadingPassage> findReadingPassageByReadingTestIdAndDeletedFalseOrderByIdAsc(Long id);
}
