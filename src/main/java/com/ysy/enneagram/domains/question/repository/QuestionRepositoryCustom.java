package com.ysy.enneagram.domains.question.repository;

import com.ysy.enneagram.domains.question.domain.Question;
import com.ysy.enneagram.domains.question.dto.QuestionResponseDto;

import java.util.List;

public interface QuestionRepositoryCustom {

    List<QuestionResponseDto> findQuestion(int maxSize);
}
