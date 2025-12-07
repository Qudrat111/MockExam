package org.example.mockexam.modules.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now();
    @LastModifiedDate
    private LocalDateTime modifiedDate;
    @CreatedBy
    @Column(updatable = false)
    private Long createdBy;
    @LastModifiedBy
    private Long lastModifiedBy;
    @Column(nullable = false)
    private Boolean deleted = false;

    public Long getId() {
        return id;
    }


    public LocalDateTime getCreatedDate() {
        return createdDate;
    }


    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }


    public Long getCreatedBy() {
        return createdBy;
    }


    public Long getLastModifiedBy() {
        return lastModifiedBy;
    }


    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
