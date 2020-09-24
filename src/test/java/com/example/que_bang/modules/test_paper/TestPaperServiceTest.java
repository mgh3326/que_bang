package com.example.que_bang.modules.test_paper;

import com.example.que_bang.modules.account.AccountRepository;
import com.example.que_bang.modules.common.BaseServiceTest;
import com.example.que_bang.modules.question.*;
import com.example.que_bang.modules.question_bundle.*;
import com.example.que_bang.modules.test_paper.query.TestPaperFlatDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TestTransaction;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestPaperServiceTest extends BaseServiceTest {
  @Autowired
  QuestionService questionService;
  @Autowired
  QuestionBundleService questionBundleService;
  @Autowired
  TestPaperService testPaperService;
  @Autowired
  QuestionBundleFactory questionBundleFactory;
  @Autowired
  QuestionFactory questionFactory;
  @Autowired
  TestPaperFactory testPaperFactory;
  @Autowired
  private AccountRepository accountRepository;


  @Test
  void add() {

    QuestionBundle questionBundle = questionBundleFactory.createQuestionBundle(2020, 5, QuestionBundleTimeZone.T1, QuestionBundlePaper.P1);
    QuestionBundle questionBundle2 = questionBundleFactory.createQuestionBundle(2020, 5, QuestionBundleTimeZone.T1, QuestionBundlePaper.P1);
    String content = "content";
    double score = 0.9;
    String answer_content = "answer_content";
    QuestionMainTopic mainTopic = QuestionMainTopic.M1;
    QuestionSubTopic subTopic = QuestionSubTopic.S1;
    Question essay = questionFactory.createQuestionWithAddQuestionBundle(QuestionType.E, content, score, answer_content, mainTopic, subTopic, questionBundle.getId());
    Question multipleChoice = questionFactory.createQuestionWithAddQuestionBundle(QuestionType.M, content, score, answer_content, mainTopic, subTopic, questionBundle.getId());
    Question shortAnswer = questionFactory.createQuestionWithAddQuestionBundle(QuestionType.S, content, score, answer_content, mainTopic, subTopic, questionBundle2.getId());
    TestPaper testPaper = testPaperFactory.createTestPaperWithAddQuestionBundle("test_paper_title", questionBundle.getId(), questionBundle2.getId());
    TestPaper testPaper1 = testPaperFactory.findOne(testPaper.getId());
    List<TestPaperQuestionBundle> testPaperQuestionBundles = testPaper1.getTestPaperQuestionBundles();
    List<QuestionBundle> questionBundles = testPaperQuestionBundles.stream().map(TestPaperQuestionBundle::getQuestionBundle).collect(toList());
    assertEquals(questionBundle, questionBundles.get(0));
    assertEquals(questionBundle2, questionBundles.get(1));
    assertNotNull(testPaperQuestionBundles.get(0).getCreatedDate());
    assertNotNull(testPaperQuestionBundles.get(0).getLastModifiedDate());
    List<TestPaperFlatDto> allByStatus = testPaperService.findAllByStatus(TestPaperStatus.READY, null, null);
    assertNotNull(allByStatus);
    assertEquals((long) allByStatus.get(0).questionBundleCount, 2);
  }

  @Test
  void remove() {

    QuestionBundle questionBundle = questionBundleFactory.createQuestionBundle(2020, 5, QuestionBundleTimeZone.T1, QuestionBundlePaper.P1);
    QuestionBundle questionBundle2 = questionBundleFactory.createQuestionBundle(2020, 5, QuestionBundleTimeZone.T1, QuestionBundlePaper.P1);
    QuestionBundle questionBundle3 = questionBundleFactory.createQuestionBundle(2020, 5, QuestionBundleTimeZone.T1, QuestionBundlePaper.P1);
    QuestionBundle questionBundle4 = questionBundleFactory.createQuestionBundle(2020, 5, QuestionBundleTimeZone.T1, QuestionBundlePaper.P1);
    String content = "content";
    double score = 0.9;
    String answer_content = "answer_content";
    QuestionMainTopic mainTopic = QuestionMainTopic.M1;
    QuestionSubTopic subTopic = QuestionSubTopic.S1;
    questionFactory.createQuestionWithAddQuestionBundle(QuestionType.E, content, score, answer_content, mainTopic, subTopic, questionBundle.getId());
    questionFactory.createQuestionWithAddQuestionBundle(QuestionType.S, content, score, answer_content, mainTopic, subTopic, questionBundle2.getId());
    questionFactory.createQuestionWithAddQuestionBundle(QuestionType.M, content, score, answer_content, mainTopic, subTopic, questionBundle3.getId());
    questionFactory.createQuestionWithAddQuestionBundle(QuestionType.M, content, score, answer_content, mainTopic, subTopic, questionBundle4.getId());

    TestPaper testPaper = testPaperFactory.createTestPaperWithAddQuestionBundle("test_paper_title", questionBundle.getId(), questionBundle2.getId());
    TestPaper testPaper2 = testPaperFactory.createTestPaperWithAddQuestionBundle("test_paper_title", questionBundle2.getId(), questionBundle3.getId());
    TestPaper testPaper3 = testPaperFactory.createTestPaperWithAddQuestionBundle("test_paper_title", questionBundle3.getId(), questionBundle4.getId());

    TestPaper one = testPaperFactory.findOne(testPaper.getId());
    List<TestPaperQuestionBundle> testPaperQuestionBundles = one.getTestPaperQuestionBundles();
    List<QuestionBundle> questionBundles = testPaperQuestionBundles.stream().map(TestPaperQuestionBundle::getQuestionBundle).collect(toList());
    assertEquals(questionBundle, questionBundles.get(0));
    assertEquals(questionBundle2, questionBundles.get(1));
    assertNotNull(testPaperQuestionBundles.get(0).getCreatedDate());
    assertNotNull(testPaperQuestionBundles.get(0).getLastModifiedDate());

    questionBundleService.deleteOne(questionBundle2.getId());
    testPaperService.deleteOne(testPaper3.getId());

    assertEquals(testPaperFactory.findOne(testPaper.getId()).getTestPaperQuestionBundles().size(), 1);
    assertEquals(testPaperFactory.findOne(testPaper2.getId()).getTestPaperQuestionBundles().size(), 1);
    assertEquals(questionBundleFactory.findOne(questionBundle3.getId()).getTestPaperQuestionBundles().size(), 1);
    assertEquals(questionBundleFactory.findOne(questionBundle4.getId()).getTestPaperQuestionBundles().size(), 0);
    testPaperQuestionBundles = one.getTestPaperQuestionBundles();
    questionBundles = testPaperQuestionBundles.stream().map(TestPaperQuestionBundle::getQuestionBundle).collect(toList());
    System.out.println("questionBundles = " + questionBundles);
  }

  @Test
  void findAllStatus() {
    List<TestPaperFlatDto> allByStatus = testPaperService.findAllByStatus(TestPaperStatus.READY, null, null);
    assertNotNull(allByStatus);
  }
}