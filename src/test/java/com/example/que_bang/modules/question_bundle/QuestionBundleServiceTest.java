package com.example.que_bang.modules.question_bundle;

import com.example.que_bang.modules.account.AccountRepository;
import com.example.que_bang.modules.common.BaseServiceTest;
import com.example.que_bang.modules.question.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.jdbc.JdbcTestUtils;

import static com.example.que_bang.modules.common.BaseWeightEntity.defaultWeight;
import static com.example.que_bang.modules.common.BaseWeightEntity.weightInterval;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionBundleServiceTest extends BaseServiceTest {
  @Autowired
  QuestionService questionService;
  @Autowired
  QuestionBundleService questionBundleService;
  @Autowired
  QuestionBundleFactory questionBundleFactory;
  @Autowired
  QuestionFactory questionFactory;
  @Autowired
  private AccountRepository accountRepository;



  @Test
  void add() {
    QuestionBundle questionBundle = questionBundleFactory.createQuestionBundle(2020, 5, QuestionBundleTimeZone.T1, QuestionBundlePaper.P1);
    String content = "content";
    double score = 0.9;
    String answer_content = "answer_content";
    QuestionMainTopic mainTopic = QuestionMainTopic.M1;
    QuestionSubTopic subTopic = QuestionSubTopic.S1;
    Question essay = questionFactory.createQuestionWithAddQuestionBundle(QuestionType.E, content, score, answer_content, mainTopic, subTopic, questionBundle.getId());
    Question multipleChoice = questionFactory.createQuestionWithAddQuestionBundle(QuestionType.M, content, score, answer_content, mainTopic, subTopic, questionBundle.getId());
    Question shortAnswer = questionFactory.createQuestionWithAddQuestionBundle(QuestionType.S, content, score, answer_content, mainTopic, subTopic, questionBundle.getId());
    QuestionBundle questionBundle1 = questionBundleService.findOneWithQuestion(questionBundle.getId());
    assertEquals(essay, questionBundle1.getQuestions().get(0));
    assertEquals(multipleChoice, questionBundle1.getQuestions().get(1));
    assertEquals(questionBundle1.getQuestions().get(0).getWeight(), defaultWeight);
    assertEquals(questionBundle1.getQuestions().get(questionBundle1.getQuestions().size() - 1).getWeight(), defaultWeight - 2 * weightInterval);
    assertEquals(shortAnswer, questionBundle1.getQuestions().get(2));
  }
}