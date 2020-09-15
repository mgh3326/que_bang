package com.example.que_bang.modules.question;

import com.example.que_bang.modules.common.BaseServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class QuestionServiceTest extends BaseServiceTest {
  @Autowired
  QuestionService questionService;

  @Test
  void add() {
    String content = "content";
    double score = 0.9;
    QuestionMainTopic mainTopic = QuestionMainTopic.M1;
    QuestionSubTopic subTopic = QuestionSubTopic.S1;
    String answer_content = "answer_content";
    Essay essay = new Essay(content, score, answer_content, mainTopic, subTopic);
    questionService.add(essay);
    Question essay1 = questionService.findOne(essay.getId());
    assertEquals(essay1.getContent(), content);
    assertNotNull(essay1.getWeight());
    assertEquals(essay1.getScore(), score);
    assertEquals(essay1.getAnswer().getContent(), answer_content);
    assertEquals(mainTopic, essay1.getMainTopic());
    assertEquals(subTopic, essay1.getSubTopic());
  }
}