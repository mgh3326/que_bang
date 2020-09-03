package com.example.que_bang.service;

import com.example.que_bang.common.BaseServiceTest;
import com.example.que_bang.domain.Answer;
import com.example.que_bang.domain.Tag;
import com.example.que_bang.domain.question.Essay;
import com.example.que_bang.domain.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class QuestionServiceTest extends BaseServiceTest {
  @Autowired
  QuestionService questionService;
  @Autowired
  TagService tagService;

  @Test
  void add() {
    String content = "content";
    double score = 0.9;
    double weight = 0.9;
    String answer_content = "answer_content";
    Answer answer = new Answer(answer_content);
    Essay essay = new Essay(content, score, weight, answer);
    questionService.add(essay);
    String tag_title = "title";
    Tag tag = Tag.builder().title(tag_title).build();
    tagService.add(tag);
    questionService.addTag(essay, tag);
    Question essay1 = questionService.findOne(essay.getId());
    assertEquals(essay1.getContent(), content);
    assertEquals(essay1.getScore(), score);
    assertEquals(essay1.getWeight(), weight);
    assertEquals(essay1.getAnswer().getContent(), answer_content);
    assertTrue(essay1.getTags().contains(tag));
  }
}