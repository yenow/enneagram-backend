package com.ysy.enneagram.domains.question.repository;

import com.ysy.enneagram.domains.question.domain.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    @DisplayName("질문 리스트 저장 후 조회 테스트")
    void saveAndFindAll() {
        // given
        Question question1 = Question.builder()
                                     .content("나는 새로운 것을 배우는 것을 좋아합니다.")
                                     .enneagramType("7")
                                     .build();
        Question question2 = Question.builder()
                                     .content("나는 다른 사람들을 돕는 것을 좋아합니다.")
                                     .enneagramType("2")
                                     .build();

        questionRepository.saveAll(List.of(question1, question2));

        // when
        List<Question> questions = questionRepository.findAll();

        // then
        assertThat(questions).hasSize(2);
    }
}
