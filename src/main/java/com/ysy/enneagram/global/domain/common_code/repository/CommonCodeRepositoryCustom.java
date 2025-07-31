package com.ysy.enneagram.global.domain.common_code.repository;

import com.ysy.enneagram.global.domain.common_code.domain.CommonCode;

import java.util.List;
import java.util.Optional;

public interface CommonCodeRepositoryCustom {
    Optional<CommonCode> findByCode(String code);

    List<CommonCode> findHierarchyByCode(String rootCode);
}
