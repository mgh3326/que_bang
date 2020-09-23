package com.example.que_bang.modules.question;

import com.example.que_bang.modules.question_bundle.QuestionBundle;
import com.example.que_bang.modules.question_bundle.QuestionBundleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionFactory {
  @Autowired
  private QuestionService questionService;
  @Autowired
  private QuestionBundleService questionBundleService;

  public Question createQuestion(QuestionType type, String content, double score, String answerContent, QuestionMainTopic mainTopic, QuestionSubTopic subTopic) {
    Question question = Question.createQuestion(type, content, score, answerContent, mainTopic, subTopic);
    questionService.add(question);
    return question;
  }

  public Question createQuestionWithAddQuestionBundle(QuestionType type, String content, double score, String answerContent, QuestionMainTopic mainTopic, QuestionSubTopic subTopic, Long questionBundleId) {
    Question question = createQuestion(type, content, score, answerContent, mainTopic, subTopic);
    QuestionBundle questionBundle = questionBundleService.findOne(questionBundleId);
    questionBundle.addQuestion(question);
    return question;
  }
}
