package com.ysy.enneagram.domains.question.service;

import com.ysy.enneagram.domains.question.dto.QuestionResponseDto;
import com.ysy.enneagram.domains.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<QuestionResponseDto> getAllQuestions() {
        return questionRepository.findQuestion(9);
    }
}
