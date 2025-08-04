package com.ysy.enneagram.domains.question.dto;

import com.ysy.enneagram.domains.question.domain.Question;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "질문 응답 DTO")
public class QuestionResponseDto {

    @Schema(description = "질문 ID")
    private Long id;

    @Schema(description = "질문 내용")
    private String content;

    @Schema(description = "에니어그램 타입")
    private String enneagramType;

    public static QuestionResponseDto fromEntity(Question question) {
        return QuestionResponseDto.builder()
                                  .id(question.getId())
                                  .content(question.getContent())
                                  .enneagramType(question.getEnneagramType())
                                  .build();
    }
}
