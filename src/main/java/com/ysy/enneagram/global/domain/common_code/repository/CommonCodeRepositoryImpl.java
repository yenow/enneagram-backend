package com.ysy.enneagram.global.domain.common_code.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ysy.enneagram.global.domain.common_code.domain.CommonCode;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.ysy.enneagram.global.domain.common_code.domain.QCommonCode.commonCode;

@RequiredArgsConstructor
public class CommonCodeRepositoryImpl implements CommonCodeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<CommonCode> findByCode(String code) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(commonCode)
                        .where(commonCode.code.eq(code))
                        .fetchOne()
        );
    }

    @Override
    public List<CommonCode> findHierarchyByCode(String rootCode) {
        String sql = """
            WITH RECURSIVE code_hierarchy AS (
                SELECT *, 0 as level
                FROM common_code
                WHERE code = :rootCode
                UNION ALL
                SELECT c.*, ch.level + 1
                FROM common_code c
                INNER JOIN code_hierarchy ch ON c.parent_code_id = ch.code_id
            )
            SELECT * FROM code_hierarchy ORDER BY level, order_number;
            """;

        Query query = em.createNativeQuery(sql, CommonCode.class);
        query.setParameter("rootCode", rootCode);

        return query.getResultList();
    }
}

