package org.example.mockexam.repositories;

import org.example.mockexam.modules.entity.ListeningTestResultItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListeningTestResultItemRepository extends BaseRepository<ListeningTestResultItem> {

    @Query(""" 
             select ltri from ListeningTestResultItem ltri
             where ltri.listeningQuestion.part.id = :listeningPartId and ltri.deleted = false
             order by ltri.id asc
            """)
    List<ListeningTestResultItem> findReadingTestResultItemByListeningPartId(Long listeningPartId);
}
