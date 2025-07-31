package com.ysy.enneagram.global.repository;

import com.ysy.enneagram.global.domain.common_code.domain.CommonCode;
import com.ysy.enneagram.global.domain.common_code.repository.CommonCodeRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class CommonCodeRepositoryTest {

    @Autowired
    private CommonCodeRepository commonCodeRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void saveAndFindCommonCode() {
        // given
        CommonCode parent = CommonCode.builder()
                .code("G001")
                .codeValue("그룹코드")
                .description("최상위 그룹코드")
                .isUse(true)
                .orderNumber(1)
                .build();
        commonCodeRepository.save(parent);

        CommonCode child = CommonCode.builder()
                .code("G001_C001")
                .parentCode(parent)
                .codeValue("자식코드")
                .description("G001의 자식코드")
                .isUse(true)
                .orderNumber(1)
                .build();
        commonCodeRepository.save(child);

        entityManager.flush();
        entityManager.clear();

        // when
        CommonCode foundParent = commonCodeRepository.findById(parent.getCodeId()).orElse(null);
        CommonCode foundChild = commonCodeRepository.findById(child.getCodeId()).orElse(null);

        // then
        assertThat(foundParent).isNotNull();
        assertThat(foundParent.getCode()).isEqualTo("G001");

        assertThat(foundChild).isNotNull();
        assertThat(foundChild.getParentCode()).isNotNull();
        assertThat(foundChild.getParentCode().getCode()).isEqualTo("G001");
    }

    @Test
    void findByCodeWithQueryDsl() {
        // given
        CommonCode code = CommonCode.builder()
                .code("TEST01")
                .codeValue("테스트코드")
                .description("QueryDSL 테스트")
                .isUse(true)
                .orderNumber(1)
                .build();
        commonCodeRepository.save(code);

        entityManager.flush();
        entityManager.clear();

        // when
        CommonCode foundCode = commonCodeRepository.findByCode("TEST01").orElse(null);

        // then
        assertThat(foundCode).isNotNull();
        assertThat(foundCode.getCode()).isEqualTo("TEST01");
    }

    @Test
    void findHierarchyByCodeWithNativeQuery() {
        // given: 3레벨 계층 구조 데이터 생성
        CommonCode root = CommonCode.builder().code("ROOT").codeValue("루트").orderNumber(1).build();
        commonCodeRepository.save(root);

        CommonCode child1 = CommonCode.builder().code("CHILD1").codeValue("자식1").parentCode(root).orderNumber(2).build();
        commonCodeRepository.save(child1);

        CommonCode child2 = CommonCode.builder().code("CHILD2").codeValue("자식2").parentCode(root).orderNumber(1).build();
        commonCodeRepository.save(child2);

        CommonCode grandchild1 = CommonCode.builder().code("GRAND1").codeValue("손자12").parentCode(child1).orderNumber(1).build();
        commonCodeRepository.save(grandchild1);

        entityManager.flush();
        entityManager.clear();

        // when
        List<CommonCode> hierarchy = commonCodeRepository.findHierarchyByCode("ROOT");

        // then
        assertThat(hierarchy).hasSize(4);
        // 쿼리에서 level, order_number 순으로 정렬했는지 확인
        assertThat(hierarchy.get(0).getCode()).isEqualTo("ROOT");   // level 0
        assertThat(hierarchy.get(1).getCode()).isEqualTo("CHILD2"); // level 1, order 1
        assertThat(hierarchy.get(2).getCode()).isEqualTo("CHILD1"); // level 1, order 2
        assertThat(hierarchy.get(3).getCode()).isEqualTo("GRAND1"); // level 2, order 1
    }
}
