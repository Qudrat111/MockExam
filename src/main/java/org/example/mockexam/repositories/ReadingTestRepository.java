package org.example.mockexam.repositories;

import org.example.mockexam.modules.entity.ReadingTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReadingTestRepository extends BaseRepository<ReadingTest> {
    Boolean existsByName(String name);

    List<ReadingTest> findReadingTestByActiveAndDeletedFalse(Boolean active);

    @Transactional
    @Modifying
    @Query("""
            update ReadingTest rt set rt.active = true
            where rt.activeDate = :activateDate
            """)
    void activateReadingTest(String activateDate);

    @Transactional
    @Modifying
    @Query("""
            update ReadingTest rt set rt.active = false
            where rt.active = true
            """)
    void disActivateReadingTest();
}
