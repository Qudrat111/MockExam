package org.example.mockexam.repositories;

import org.example.mockexam.modules.entity.ReadingTestResultItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingTestResultItemRepository extends BaseRepository<ReadingTestResultItem> {

    @Query(""" 
             select rtri from ReadingTestResultItem rtri
             where rtri.readingQuestion.passage.id = :readingPassageId and rtri.deleted = false
             order by rtri.id asc
            """)
    List<ReadingTestResultItem> findReadingTestResultItemByReadingPassageId(Long readingPassageId);
}
