package com.example.que_bang.modules.question;

import com.example.que_bang.modules.common.BaseServiceTest;
import com.example.que_bang.modules.common.BaseWeightEntity;
import com.example.que_bang.modules.question_bundle.QuestionBundle;
import com.example.que_bang.modules.question_bundle.QuestionBundleFactory;
import com.example.que_bang.modules.question_bundle.QuestionBundlePaper;
import com.example.que_bang.modules.question_bundle.QuestionBundleTimeZone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class QuestionServiceTest extends BaseServiceTest {
  @Autowired
  QuestionService questionService;
  @Autowired
  QuestionBundleFactory questionBundleFactory;
  @Autowired
  QuestionFactory questionFactory;

  @Test
  void add() {
    QuestionBundle questionBundle = questionBundleFactory.createQuestionBundle(2020, 5, QuestionBundleTimeZone.T1, QuestionBundlePaper.P1);
    String content = "content";
    double score = 0.9;
    String answer_content = "answer_content";
    QuestionMainTopic mainTopic = QuestionMainTopic.M1;
    QuestionSubTopic subTopic = QuestionSubTopic.S1;
    Question essay = questionFactory.createQuestionWithAddQuestionBundle(QuestionType.E, content, score, answer_content, mainTopic, subTopic, questionBundle.getId());
    Question essay1 = questionService.findOne(essay.getId());
    assertEquals(essay1.getContent(), content);
    assertNotNull(essay1.getWeight());
    assertEquals(essay1.getScore(), score);
    assertEquals(essay1.getAnswer().getContent(), answer_content);
    assertEquals(mainTopic, essay1.getMainTopic());
    assertEquals(subTopic, essay1.getSubTopic());
  }

  @Test
  void weight() {
    QuestionBundle questionBundle = questionBundleFactory.createQuestionBundle(2020, 5, QuestionBundleTimeZone.T1, QuestionBundlePaper.P1);
    String content = "content";
    double score = 0.9;
    String answer_content = "answer_content";
    QuestionMainTopic mainTopic = QuestionMainTopic.M1;
    QuestionSubTopic subTopic = QuestionSubTopic.S1;
    Question essay = questionFactory.createQuestionWithAddQuestionBundle(QuestionType.E, content, score, answer_content, mainTopic, subTopic, questionBundle.getId());
    Question essay1 = questionFactory.createQuestionWithAddQuestionBundle(QuestionType.E, content, score, answer_content, mainTopic, subTopic, questionBundle.getId());
    Question essay2 = questionFactory.createQuestionWithAddQuestionBundle(QuestionType.E, content, score, answer_content, mainTopic, subTopic, questionBundle.getId());
    assertEquals(BaseWeightEntity.defaultWeight, questionService.findOne(essay.getId()).getWeight());
    assertEquals(BaseWeightEntity.defaultWeight - BaseWeightEntity.weightInterval, questionService.findOne(essay1.getId()).getWeight());
    assertEquals(BaseWeightEntity.defaultWeight - BaseWeightEntity.weightInterval * 2, questionService.findOne(essay2.getId()).getWeight());
    questionService.changeWeight(essay.getId(), 1);
    assertTrue(questionService.findOne(essay1.getId()).getWeight() > questionService.findOne(essay.getId()).getWeight());
    assertEquals((questionService.findOne(essay1.getId()).getWeight() + questionService.findOne(essay2.getId()).getWeight()) / 2, questionService.findOne(essay.getId()).getWeight());
    questionService.changeWeight(essay.getId(), 0);
    assertTrue(questionService.findOne(essay.getId()).getWeight() > questionService.findOne(essay1.getId()).getWeight());
    assertEquals(questionService.findOne(essay1.getId()).getWeight() + BaseWeightEntity.weightInterval, questionService.findOne(essay.getId()).getWeight());
  }
}