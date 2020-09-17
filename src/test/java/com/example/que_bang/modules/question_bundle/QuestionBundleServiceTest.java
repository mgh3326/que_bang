package com.example.que_bang.modules.question_bundle;

import com.example.que_bang.modules.common.BaseServiceTest;
import com.example.que_bang.modules.question.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionBundleServiceTest extends BaseServiceTest {
  @Autowired
  QuestionService questionService;
  @Autowired
  QuestionBundleService questionBundleService;

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
    QuestionBundle questionBundle = QuestionBundle.createQuestionBundleWithQuestions(2020, 5, QuestionBundleTimeZone.T1, QuestionBundlePaper.P1, essay, multipleChoice, shortAnswer);
    questionBundleService.add(questionBundle);
    QuestionBundle questionBundle1 = questionBundleService.findOne(questionBundle.getId());
    assertEquals(essay, questionBundle1.getQuestions().get(0));
    assertEquals(multipleChoice, questionBundle1.getQuestions().get(1));
    assertEquals(questionBundle1.getQuestions().get(0).getWeight(), QuestionBundle.defaultWeight);
    assertEquals(questionBundle1.getQuestions().get(questionBundle1.getQuestions().size() - 1).getWeight(), QuestionBundle.defaultWeight - 2 * QuestionBundle.weightInterval);
    assertEquals(shortAnswer, questionBundle1.getQuestions().get(2));
  }
}