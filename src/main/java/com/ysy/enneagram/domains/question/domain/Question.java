package com.ysy.enneagram.domains.question.domain;

import com.ysy.enneagram.global.domain.common_code.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "question")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("ID")
    @Column(name = "id")
    private Long id;

    @Comment("내용")
    @Column(name = "content", length = 1000, nullable = false)
    private String content;

    @Comment("에니어그램 타입")
    @Column(name = "enneagram_type", length = 2)
    private String enneagramType;

    @Comment("사용여부")
    @Column(name = "is_use", columnDefinition = "TINYINT(1) default 0")
    private boolean isUse;
}
