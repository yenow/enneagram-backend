package com.ysy.enneagram.global.domain.common_code.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@ToString
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto {
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private LocalDateTime deletionDate;
    private boolean isDelete;
}
