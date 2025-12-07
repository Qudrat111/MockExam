package org.example.mockexam.repositories;

import org.example.mockexam.modules.entity.ListeningTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ListeningTestRepository extends BaseRepository<ListeningTest> {
    Boolean existsByName(String name);

    List<ListeningTest> findListeningTestByActiveAndDeletedFalse(Boolean active);

    @Transactional
    @Modifying
    @Query("""
            update ListeningTest lt set lt.active = true
            where lt.activeDate = :activateDate
            """)
    void activateListeningTest(String activateDate);

    @Transactional
    @Modifying
    @Query("""
            update ListeningTest lt set lt.active = false
            where lt.active = true
            """)
    void disActivateListeningTest();
}
