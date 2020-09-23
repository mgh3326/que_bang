package com.example.que_bang.modules.test_paper;

import com.example.que_bang.modules.common.BaseServiceTest;
import com.example.que_bang.modules.question.*;
import com.example.que_bang.modules.question_bundle.*;
import com.example.que_bang.modules.test_paper.query.TestPaperFlatDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
    TestPaper testPaper1 = testPaperService.findOne(testPaper.getId());
    List<TestPaperQuestionBundle> testPaperQuestionBundles = testPaper1.getTestPaperQuestionBundles();
    List<QuestionBundle> questionBundles = testPaperQuestionBundles.stream().map(TestPaperQuestionBundle::getQuestionBundle).collect(toList());
    assertEquals(questionBundle, questionBundles.get(0));
    assertEquals(questionBundle2, questionBundles.get(1));
    assertNotNull(testPaper1.getCreatedDate());
    assertNotNull(testPaper1.getLastModifiedDate());
    List<TestPaperFlatDto> allByStatus = testPaperService.findAllByStatus(TestPaperStatus.READY, null, null);
    assertNotNull(allByStatus);
    assertEquals((long) allByStatus.get(0).questionBundleCount, 2);
  }

  @Test
  void findAllStatus() {
    List<TestPaperFlatDto> allByStatus = testPaperService.findAllByStatus(TestPaperStatus.READY, null, null);
    assertNotNull(allByStatus);
  }
}