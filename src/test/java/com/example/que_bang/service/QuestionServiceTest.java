package com.example.que_bang.service;

import com.example.que_bang.common.BaseServiceTest;
import com.example.que_bang.domain.Answer;
import com.example.que_bang.domain.Topic;
import com.example.que_bang.domain.question.Essay;
import com.example.que_bang.domain.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class QuestionServiceTest extends BaseServiceTest {
  @Autowired
  QuestionService questionService;
  @Autowired
  TopicService topicService;

  @Test
  void add() {
    String content = "content";
    double weight = 0.9;
    String answer_content = "answer_content";
    Answer answer = new Answer(answer_content);
    Essay essay = new Essay(content, weight, answer);
    questionService.add(essay);
    String topic_title = "title1";
    Topic topic = Topic.createTopic(topic_title);
    String topic_title2 = "title2";
    Topic topic2 = Topic.createTopic(topic_title2);
    topicService.add(topic);
    topicService.add(topic2);
    topic.addChildTopic(topic2);
    questionService.addTag(essay, topic);
    questionService.addTag(essay, topic2);
    Question essay1 = questionService.findOne(essay.getId());
    assertEquals(essay1.getContent(), content);
    assertNotNull(essay1.getScore());
    assertEquals(essay1.getWeight(), weight);
    assertEquals(essay1.getAnswer().getContent(), answer_content);
    assertTrue(essay1.getTopics().contains(topic2));
  }
}