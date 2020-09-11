package com.example.que_bang.service;

import com.example.que_bang.common.BaseServiceTest;
import com.example.que_bang.domain.QuestionBundle;
import com.example.que_bang.domain.Topic;
import com.example.que_bang.domain.question.Essay;
import com.example.que_bang.domain.question.MultipleChoice;
import com.example.que_bang.domain.question.ShortAnswer;
import com.example.que_bang.domain.question_bundle.QuestionBundlePaper;
import com.example.que_bang.domain.question_bundle.QuestionBundleTimeZone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.que_bang.question_bundle.QuestionBundleService;

import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestionBundleServiceTest extends BaseServiceTest {
  @Autowired
  QuestionService questionService;
  @Autowired
  TopicService topicService;
  @Autowired
  QuestionBundleService questionBundleService;

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
    double score = 0.9;
    double weight = 0.9;
    String answer_content = "answer_content";

    Essay essay = Essay.createEssayWithAnswerContent(content, score, weight, answer_content);
    questionService.add(essay);
    questionService.addTag(essay, topic);
    questionService.addTag(essay, topic2);

    ShortAnswer shortAnswer = ShortAnswer.createShortAnswerWithAnswerContent(content, score, weight, answer_content);
    questionService.add(shortAnswer);
    questionService.addTag(shortAnswer, topic);
    questionService.addTag(shortAnswer, topic2);

    MultipleChoice multipleChoice = MultipleChoice.createMultipleChoiceWithAnswerContent(content, score, weight, answer_content);
    questionService.add(multipleChoice);
    questionService.addTag(multipleChoice, topic);
    questionService.addTag(multipleChoice, topic2);
    QuestionBundle questionBundle = QuestionBundle.createQuestionBundleWithQuestions(2020, 5, QuestionBundleTimeZone.TZ1, QuestionBundlePaper.P1, essay, multipleChoice, shortAnswer);
    questionBundleService.add(questionBundle);
    QuestionBundle questionBundle1 = questionBundleService.findOne(questionBundle.getId());
    assertTrue(questionBundle1.getQuestions().contains(essay));
    assertTrue(questionBundle1.getQuestions().contains(multipleChoice));
    assertTrue(questionBundle1.getQuestions().contains(shortAnswer));
  }
}