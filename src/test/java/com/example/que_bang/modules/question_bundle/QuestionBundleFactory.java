package com.example.que_bang.modules.question_bundle;

import com.example.que_bang.modules.question.QuestionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionBundleFactory {
  @Autowired
  private QuestionBundleService questionBundleService;

  public QuestionBundle createQuestionBundle(int year, int month, QuestionBundleTimeZone timeZone, QuestionBundlePaper paper) {
    QuestionBundle questionBundle = QuestionBundle.createQuestionBundle(year, month, timeZone, paper);
    questionBundleService.add(questionBundle);
    return questionBundle;
  }
}
