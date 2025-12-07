package org.example.mockexam.repositories;

import org.example.mockexam.modules.entity.WritingTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WritingTestRepository extends BaseRepository<WritingTest> {
    Boolean existsByName(String name);

    List<WritingTest> findWritingTestByActiveAndDeletedFalse(Boolean active);

    @Transactional
    @Modifying
    @Query("""
            update WritingTest wt set wt.active = true
            where wt.activeDate = :activateDate
            """)
    void activateWritingTest(String activateDate);

    @Transactional
    @Modifying
    @Query("""
            update WritingTest wt set wt.active = false
            where wt.active = true
            """)
    void disActivateWritingTest();
}
