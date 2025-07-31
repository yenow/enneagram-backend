package com.ysy.enneagram.global.domain.common_code.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "common_code")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommonCode extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("코드 ID")
    @Column(name = "code_id")
    private Long codeId;

    @Comment("코드")
    @Column(name = "code", length = 100, unique = true, nullable = false)
    private String code;

    @Comment("부모 코드 ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_code_id")
    private CommonCode parentCode;

    @Builder.Default
    @OneToMany(mappedBy = "parentCode", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommonCode> children = new ArrayList<>();

    @Comment("코드 값")
    @Column(name = "code_value", length = 200, nullable = false)
    private String codeValue;

    @Comment("코드 설명")
    @Column(name = "description", length = 1000)
    private String description;

    @Builder.Default
    @Comment("사용 여부 (1: 사용, 0: 미사용)")
    @Column(name = "is_use", columnDefinition = "TINYINT(1) default 1", nullable = false)
    private boolean isUse = true;

    @Comment("정렬 순번")
    @Column(name = "order_number")
    private Integer orderNumber;
}
