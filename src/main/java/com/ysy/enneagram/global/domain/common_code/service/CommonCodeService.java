package com.ysy.enneagram.global.domain.common_code.service;

import com.ysy.enneagram.global.domain.common_code.domain.CommonCode;
import com.ysy.enneagram.global.domain.common_code.dto.CommonCodeResponseDto;
import com.ysy.enneagram.global.domain.common_code.repository.CommonCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommonCodeService {

    private final CommonCodeRepository commonCodeRepository;

    public List<CommonCodeResponseDto> findHierarchyByCode(String rootCode) {
        List<CommonCode> flatList = commonCodeRepository.findHierarchyByCode(rootCode);
        if (flatList.isEmpty()) {
            return new ArrayList<>();
        }

        Map<Long, CommonCodeResponseDto> map = new HashMap<>();
        List<CommonCodeResponseDto> rootNodes = new ArrayList<>();

        // 1. 모든 노드를 DTO로 변환하고 맵에 저장
        for (CommonCode code : flatList) {
            CommonCodeResponseDto dto = CommonCodeResponseDto.builder()
                    .codeId(code.getCodeId())
                    .code(code.getCode())
                    .codeValue(code.getCodeValue())
                    .description(code.getDescription())
                    .children(new ArrayList<>()) // 자식 리스트 초기화
                    .build();
            map.put(dto.getCodeId(), dto);
        }

        // 2. 부모-자식 관계 설정
        for (CommonCode code : flatList) {
            if (code.getParentCode() != null) {
                CommonCodeResponseDto parentDto = map.get(code.getParentCode().getCodeId());
                CommonCodeResponseDto childDto = map.get(code.getCodeId());
                if (parentDto != null) {
                    parentDto.getChildren().add(childDto);
                }
            } else {
                // 부모가 없는 노드가 최상위 노드(루트)
                rootNodes.add(map.get(code.getCodeId()));
            }
        }
        return rootNodes;
    }
}

