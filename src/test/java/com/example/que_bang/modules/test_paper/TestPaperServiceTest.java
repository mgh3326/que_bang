package com.example.que_bang.modules.test_paper;

import com.example.que_bang.modules.common.BaseServiceTest;
import com.example.que_bang.modules.question.*;
import com.example.que_bang.modules.question_bundle.QuestionBundle;
import com.example.que_bang.modules.question_bundle.QuestionBundlePaper;
import com.example.que_bang.modules.question_bundle.QuestionBundleService;
import com.example.que_bang.modules.question_bundle.QuestionBundleTimeZone;
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

  @Test
  void add() {

    String content = "content";
    double weight = 0.9;
    String answer_content = "answer_content";
    QuestionMainTopic mainTopic = QuestionMainTopic.M1;
    QuestionSubTopic subTopic = QuestionSubTopic.S1;
    Essay essay = Essay.createEssayWithAnswerContent(content, weight, answer_content, mainTopic, subTopic);
    questionService.add(essay);

    ShortAnswer shortAnswer = ShortAnswer.createShortAnswerWithAnswerContent(content, weight, answer_content, mainTopic, subTopic);
    questionService.add(shortAnswer);

    MultipleChoice multipleChoice = MultipleChoice.createMultipleChoiceWithAnswerContent(content, weight, answer_content, mainTopic, subTopic);
    questionService.add(multipleChoice);
    QuestionBundle questionBundle = QuestionBundle.createQuestionBundleWithQuestions(2020, 5, QuestionBundleTimeZone.T1, QuestionBundlePaper.P1, essay, multipleChoice);
    QuestionBundle questionBundle2 = QuestionBundle.createQuestionBundleWithQuestions(2020, 5, QuestionBundleTimeZone.T2, QuestionBundlePaper.P2, shortAnswer);
    questionBundleService.add(questionBundle);
    questionBundleService.add(questionBundle2);
    TestPaperQuestionBundle testPaperQuestionBundle = TestPaperQuestionBundle.createWithQuestionBundle(questionBundle);
    TestPaper testPaper = TestPaper.createTestPaperWithTestPaperQuestionBundles("test_paper_title", testPaperQuestionBundle);
    testPaperService.add(testPaper);
    // test paper에 com.example.que_bang.modules.question_bundle 하나 더 추가
    TestPaperQuestionBundle testPaperQuestionBundle2 = TestPaperQuestionBundle.createWithQuestionBundle(questionBundle2);
    testPaper.addTestPaperQuestionBundle(testPaperQuestionBundle2);
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