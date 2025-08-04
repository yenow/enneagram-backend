package com.ysy.enneagram.global.domain.common_code.controller;

import com.ysy.enneagram.global.domain.common_code.dto.CommonCodeResponseDto;
import com.ysy.enneagram.global.domain.common_code.service.CommonCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/common-codes")
@RequiredArgsConstructor
public class CommonCodeApiController {

    private final CommonCodeService commonCodeService;

    @GetMapping("/{code}/hierarchy")
    public ResponseEntity<List<CommonCodeResponseDto>> getHierarchyByCode(@PathVariable String code) {
        List<CommonCodeResponseDto> hierarchy = commonCodeService.findHierarchyByCode(code);
        if (hierarchy.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hierarchy);
    }
}

