package com.ysy.enneagram.global.domain.common_code.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

    @Comment("생성일시")
    @CreatedDate
    @Column(name = "creation_date", updatable = false, nullable = false)
    private LocalDateTime creationDate;

    @Comment("수정일시")
    @LastModifiedDate
    @Column(name = "modification_date")
    private LocalDateTime modificationDate;

    @Comment("삭제일시")
    @Column(name = "deletion_date")
    private LocalDateTime deletionDate;

    @Comment("삭제여부")
    @Builder.Default
    @Column(name = "is_delete", columnDefinition = "TINYINT(1) default 0")
    private boolean isDelete = false;

    public void delete() {
        this.isDelete = true;
        this.deletionDate = LocalDateTime.now();
    }
}
