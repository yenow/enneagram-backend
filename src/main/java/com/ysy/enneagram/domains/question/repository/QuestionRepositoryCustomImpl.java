package com.ysy.enneagram.domains.question.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ysy.enneagram.domains.question.dto.QuestionResponseDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Random;

import static com.ysy.enneagram.domains.question.domain.QQuestion.question;

@RequiredArgsConstructor
public class QuestionRepositoryCustomImpl implements QuestionRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<QuestionResponseDto> findQuestion(int maxSize) {

        return queryFactory.select(Projections.fields(QuestionResponseDto.class, question.id, question.content, question.enneagramType))
                           .from(question)
                           .where(question.isUse.eq(true)
                                                .and(question.isDelete.eq(false)))
                           .fetch()
                           .stream()
                           .collect(java.util.stream.Collectors.groupingBy(QuestionResponseDto::getEnneagramType))
                           .values()
                           .stream()
                           .flatMap(list -> {
                               Random random = new Random();
                               return random.ints(0, list.size())
                                            .distinct()
                                            .limit(maxSize)
                                            .mapToObj(list::get);
                           })
                           .collect(java.util.stream.Collectors.toList());
    }
}
