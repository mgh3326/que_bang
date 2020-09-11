package com.example.que_bang.service;

import com.example.que_bang.common.BaseServiceTest;
import com.example.que_bang.domain.QuestionBundle;
import com.example.que_bang.domain.TestPaper;
import com.example.que_bang.domain.Topic;
import com.example.que_bang.domain.question.Essay;
import com.example.que_bang.domain.question.MultipleChoice;
import com.example.que_bang.domain.question.ShortAnswer;
import com.example.que_bang.domain.question_bundle.QuestionBundlePaper;
import com.example.que_bang.domain.question_bundle.QuestionBundleTimeZone;
import com.example.que_bang.domain.test_paper.TestPaperQuestionBundle;
import com.example.que_bang.domain.test_paper.TestPaperStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.que_bang.question_bundle.QuestionBundleService;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

class TestPaperServiceTest extends BaseServiceTest {
  @Autowired
  QuestionService questionService;
  @Autowired
  TopicService topicService;
  @Autowired
  QuestionBundleService questionBundleService;
  @Autowired
  TestPaperService testPaperService;

  @Test
  void add() {
    // create topic
    String topic_title = "title1";
    Topic topic = Topic.createTopic(topic_title);
    String topic_title2 = "title2";
    Topic topic2 = Topic.createTopic(topic_title2);
    topicService.add(topic);
    topicService.add(topic2);
    topic.addChildTopic(topic2);

    String content = "content";
    double weight = 0.9;
    String answer_content = "answer_content";

    Essay essay = Essay.createEssayWithAnswerContent(content, weight, answer_content);
    questionService.add(essay);
    questionService.addTag(essay, topic);
    questionService.addTag(essay, topic2);

    ShortAnswer shortAnswer = ShortAnswer.createShortAnswerWithAnswerContent(content, weight, answer_content);
    questionService.add(shortAnswer);
    questionService.addTag(shortAnswer, topic);
    questionService.addTag(shortAnswer, topic2);

    MultipleChoice multipleChoice = MultipleChoice.createMultipleChoiceWithAnswerContent(content, weight, answer_content);
    questionService.add(multipleChoice);
    questionService.addTag(multipleChoice, topic);
    questionService.addTag(multipleChoice, topic2);
    QuestionBundle questionBundle = QuestionBundle.createQuestionBundleWithQuestions(2020, 5, QuestionBundleTimeZone.TZ1, QuestionBundlePaper.P1, essay, multipleChoice);
    QuestionBundle questionBundle2 = QuestionBundle.createQuestionBundleWithQuestions(2020, 5, QuestionBundleTimeZone.TZ2, QuestionBundlePaper.P2, shortAnswer);
    questionBundleService.add(questionBundle);
    questionBundleService.add(questionBundle2);
    TestPaperQuestionBundle testPaperQuestionBundle = TestPaperQuestionBundle.createWithQuestionBundle(questionBundle);
    TestPaper testPaper = TestPaper.createTestPaperWithTestPaperQuestionBundles("test_paper_title", TestPaperStatus.READY, testPaperQuestionBundle);
    testPaperService.add(testPaper);
    // test paper에 com.example.que_bang.question_bundle 하나 더 추가
    TestPaperQuestionBundle testPaperQuestionBundle2 = TestPaperQuestionBundle.createWithQuestionBundle(questionBundle2);
    testPaper.addTestPaperQuestionBundle(testPaperQuestionBundle2);
    TestPaper testPaper1 = testPaperService.findOne(testPaper.getId());
    List<TestPaperQuestionBundle> testPaperQuestionBundles = testPaper1.getTestPaperQuestionBundles();
    List<QuestionBundle> questionBundles = testPaperQuestionBundles.stream().map(TestPaperQuestionBundle::getQuestionBundle).collect(toList());
    assertEquals(questionBundle, questionBundles.get(0));
    assertEquals(questionBundle2, questionBundles.get(1));
    assertNotNull(testPaper1.getCreatedDate());
    assertNotNull(testPaper1.getLastModifiedDate());
  }
}