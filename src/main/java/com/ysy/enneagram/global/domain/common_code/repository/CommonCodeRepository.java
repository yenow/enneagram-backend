package com.ysy.enneagram.global.domain.common_code.repository;

import com.ysy.enneagram.global.domain.common_code.domain.CommonCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonCodeRepository extends JpaRepository<CommonCode, Long>, CommonCodeRepositoryCustom {
}
