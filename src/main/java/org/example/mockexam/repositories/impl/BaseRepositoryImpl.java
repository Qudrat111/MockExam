package org.example.mockexam.repositories.impl;

import jakarta.persistence.EntityManager;
import org.example.mockexam.modules.entity.BaseEntity;
import org.example.mockexam.repositories.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BaseRepositoryImpl<T extends BaseEntity>
        extends SimpleJpaRepository<T, Long>
        implements BaseRepository<T> {

    private final EntityManager entityManager;

    private final Specification<T> isNotDeletedSpecification = (root, query, cb) ->
            cb.equal(root.get("deleted"), false);

    public BaseRepositoryImpl(JpaEntityInformation<T, Long> entityInformation,
                              EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<T> findByIdAndDeletedFalse(Long id) {
        return findById(id).filter(e -> Boolean.FALSE.equals(e.getDeleted()));
    }

    @Override
    @Transactional
    public T trash(Long id) {
        T entity = findById(id).orElse(null);
        if (entity != null && Boolean.FALSE.equals(entity.getDeleted())) {
            entity.setDeleted(true);
            return save(entity);
        }
        return entity;
    }

    @Override
    @Transactional
    public List<T> trashList(List<Long> ids) {
        return ids.stream()
                .map(this::trash)
                .collect(Collectors.toList());
    }

    @Override
    public List<T> findAllNotDeleted() {
        return findAll(isNotDeletedSpecification);
    }

    @Override
    public Page<T> findAllNotDeleted(Pageable pageable) {
        return findAll(isNotDeletedSpecification, pageable);
    }
}

