package org.example.mockexam.repositories;

import org.example.mockexam.modules.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
    Optional<T> findByIdAndDeletedFalse(Long id);

    T trash(Long id);

    List<T> trashList(List<Long> ids);

    List<T> findAllNotDeleted();

    Page<T> findAllNotDeleted(Pageable pageable);
}
