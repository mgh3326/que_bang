package com.example.que_bang.service;

import com.example.que_bang.common.BaseServiceTest;
import com.example.que_bang.domain.Question;
import com.example.que_bang.domain.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class QuestionServiceTest extends BaseServiceTest {
  @Autowired
  QuestionService questionService;

  @Autowired
  TagService tagService;

  @Test
  @DisplayName("Question 저장")
  void add() {
    String content = "content";
    Question question = Question.builder().content(content).build();
    questionService.add(question);
    String tag_title = "title";
    Tag tag = Tag.builder().title(tag_title).build();
    tagService.add(tag);
    questionService.addTag(question, tag);
    Question question1 = questionService.findOne(question.getId());
    assertEquals(question1.getContent(), content);
    assertTrue(question1.getTags().contains(tag));
  }
}