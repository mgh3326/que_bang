package com.example.que_bang.modules.test_paper.query;


import com.example.que_bang.modules.question.QQuestion;
import com.example.que_bang.modules.question.Question;
import com.example.que_bang.modules.question_bundle.QQuestionBundle;
import com.example.que_bang.modules.test_paper.QTestPaperQuestionBundle;
import com.example.que_bang.modules.test_paper.TestPaperStatus;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.que_bang.modules.question.QQuestion.question;
import static com.example.que_bang.modules.question_bundle.QQuestionBundle.questionBundle;
import static com.example.que_bang.modules.test_paper.QTestPaper.testPaper;

@Repository
@RequiredArgsConstructor
public class TestPaperQueryRepository {
  private final EntityManager em;

  public List<TestPaperFlatDto> findAll(TestPaperStatus status, Long ignoreQuestionBundleId, Long questionBundleId) {
    JPAQueryFactory query = new JPAQueryFactory(em);
    QQuestionBundle questionBundle = QQuestionBundle.questionBundle;
    QTestPaperQuestionBundle testPaperQuestionBundle = QTestPaperQuestionBundle.testPaperQuestionBundle;
    return query
            .select(Projections.constructor(TestPaperFlatDto.class,
                    testPaper,
                    ExpressionUtils.as(
                            JPAExpressions.select(testPaperQuestionBundle.count())
                                    .from(testPaperQuestionBundle)
                                    .where(testPaperQuestionBundle.testPaper.eq(testPaper)),
                            "testPaperQuestionBundleCount"
                    )))
            .from(testPaper)
            .where(statusEq(status))
            .leftJoin(testPaper.testPaperQuestionBundles, testPaperQuestionBundle)
            .leftJoin(testPaperQuestionBundle.questionBundle, questionBundle)
            .where(questionBundleNe(ignoreQuestionBundleId))
            .where(questionBundleEq(questionBundleId))
            .fetch().stream().distinct().collect(Collectors.toList());
  }


  public Optional<TestPaperQueryDto> findAllWithQuestionBundle(Long id) {
    JPAQueryFactory query = new JPAQueryFactory(em);
    QQuestionBundle questionBundle = QQuestionBundle.questionBundle;
    QTestPaperQuestionBundle testPaperQuestionBundle = QTestPaperQuestionBundle.testPaperQuestionBundle;
    return Optional.ofNullable(query
            .select(Projections.constructor(TestPaperQueryDto.class,
                    testPaper,
                    ExpressionUtils.as(
                            JPAExpressions.select(testPaperQuestionBundle.count())
                                    .from(testPaperQuestionBundle)
                                    .where(testPaperQuestionBundle.testPaper.eq(testPaper)),
                            "testPaperQuestionBundleCount"
                    ),
                    ExpressionUtils.as(
                            JPAExpressions.select(question.count())
                                    .from(question)
                                    .where(question.questionBundle.eq(questionBundle)),
                            "questionCount"
                    )
            ))
            .from(testPaper)
            .where(testPaper.id.eq(id))
            .leftJoin(testPaper.testPaperQuestionBundles, testPaperQuestionBundle)
            .fetchJoin()
            .leftJoin(testPaperQuestionBundle.questionBundle, questionBundle)
            .fetchJoin()
            .fetchFirst());
  }

  public List<Question> getQuestions(Long id) {
    JPAQueryFactory query = new JPAQueryFactory(em);
    QQuestionBundle questionBundle = QQuestionBundle.questionBundle;
    QTestPaperQuestionBundle testPaperQuestionBundle = QTestPaperQuestionBundle.testPaperQuestionBundle;
    return query
            .selectFrom(question)
            .innerJoin(question.questionBundle, questionBundle)
            .innerJoin(questionBundle.testPaperQuestionBundles, testPaperQuestionBundle)
            .innerJoin(testPaperQuestionBundle.testPaper, testPaper)
            .where(testPaper.id.eq(id))
            .fetch();
  }

  private BooleanExpression statusEq(TestPaperStatus statusCond) {
    if (statusCond == null) {
      return null;
    }
    return testPaper.status.eq(statusCond);
  }

  private BooleanExpression questionBundleNe(Long ignoreQuestionBundleId) {
    if (ignoreQuestionBundleId == null) {
      return null;
    }
    return questionBundle.id.ne(ignoreQuestionBundleId).or(questionBundle.id.isNull());
  }

  private BooleanExpression questionBundleEq(Long questionBundleId) {
    if (questionBundleId == null) {
      return null;
    }
    return questionBundle.id.eq(questionBundleId);
  }
}
