package com.example.que_bang.modules.test_paper.query;


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

import static com.example.que_bang.modules.test_paper.QTestPaper.testPaper;

@Repository
@RequiredArgsConstructor
public class TestPaperQueryRepository {
  private final EntityManager em;

  public List<TestPaperFlatDto> findAll(TestPaperStatus status) {

    JPAQueryFactory query = new JPAQueryFactory(em);
    QTestPaperQuestionBundle testPaperQuestionBundle = QTestPaperQuestionBundle.testPaperQuestionBundle;
    return query
            .select(Projections.constructor(TestPaperFlatDto.class,
                    testPaper.id,
                    testPaper.title,
                    ExpressionUtils.as(
                            JPAExpressions.select(testPaperQuestionBundle.count())
                                    .from(testPaperQuestionBundle)
                                    .where(testPaperQuestionBundle.testPaper.eq(testPaper)),
                            "testPaperQuestionBundleCount"
                    )))
            .from(testPaper)
            .where(statusEq(status))
            .fetch();
  }

  private BooleanExpression statusEq(TestPaperStatus statusCond) {
    if (statusCond == null) {
      return null;
    }
    return testPaper.status.eq(statusCond);
  }
}
