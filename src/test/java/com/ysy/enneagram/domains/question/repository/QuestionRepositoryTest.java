package com.ysy.enneagram.domains.question.repository;

import com.ysy.enneagram.domains.question.dto.QuestionResponseDto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    @DisplayName("질문 리스트 가져오기")
    void findQuestion() {
        List<QuestionResponseDto> question = questionRepository.findQuestion(9);
        assertThat(question.size()).isEqualTo(81);
    }
}
