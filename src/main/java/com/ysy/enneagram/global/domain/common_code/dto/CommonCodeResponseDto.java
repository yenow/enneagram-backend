package com.ysy.enneagram.global.domain.common_code.dto;

import com.ysy.enneagram.global.domain.common_code.domain.CommonCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "공통 코드 응답 DTO")
public class CommonCodeResponseDto {

    @Schema(description = "코드 ID (자동 생성)")
    private Long codeId;

    @Schema(description = "코드 (비즈니스 식별자)")
    private String code;

    @Schema(description = "코드 값")
    private String codeValue;

    @Schema(description = "코드 설명")
    private String description;

    @Schema(description = "자식 코드 목록")
    private List<CommonCodeResponseDto> children;

    public static CommonCodeResponseDto fromEntity(CommonCode code) {
        return CommonCodeResponseDto.builder()
                .codeId(code.getCodeId())
                .code(code.getCode())
                .codeValue(code.getCodeValue())
                .description(code.getDescription())
                .children(code.getChildren().stream().map(CommonCodeResponseDto::fromEntity).collect(Collectors.toList()))
                .build();
    }
}

