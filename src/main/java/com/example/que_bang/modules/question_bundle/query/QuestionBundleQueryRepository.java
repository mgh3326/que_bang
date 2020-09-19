package com.example.que_bang.modules.question_bundle.query;


import com.example.que_bang.modules.question.QQuestion;
import com.example.que_bang.modules.question_bundle.QQuestionBundle;
import com.example.que_bang.modules.question_bundle.QuestionBundle;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QuestionBundleQueryRepository {
  private final EntityManager em;

  public List<QuestionBundleFlatDto> findAll() {
    JPAQueryFactory query = new JPAQueryFactory(em);
    QQuestionBundle questionBundle = QQuestionBundle.questionBundle;
    QQuestion question = QQuestion.question;
    return query
            .select(Projections.constructor(QuestionBundleFlatDto.class,
                    questionBundle,
                    ExpressionUtils.as(
                            JPAExpressions.select(question.count())
                                    .from(question)
                                    .where(question.questionBundle.eq(questionBundle)),
                            "questionCount"
                    )))
            .from(questionBundle)
            .fetch();
  }

  public Optional<QuestionBundle> findOneWithQuestion(Long id) {
    JPAQueryFactory query = new JPAQueryFactory(em);
    QQuestionBundle questionBundle = QQuestionBundle.questionBundle;
    QQuestion question = QQuestion.question;
    return Optional.ofNullable(query
            .selectFrom(questionBundle)
            .where(questionBundle.id.eq(id))
            .leftJoin(questionBundle.questions, question)
            .fetchJoin()
            .fetchOne());
  }
}
