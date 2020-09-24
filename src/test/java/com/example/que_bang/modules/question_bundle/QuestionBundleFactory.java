package com.example.que_bang.modules.question_bundle;

import com.example.que_bang.modules.question.QuestionFactory;
import com.example.que_bang.modules.test_paper.TestPaper;
import com.example.que_bang.modules.test_paper.TestPaperQuestionBundle;
import com.example.que_bang.modules.test_paper.TestPaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
@Transactional
public class QuestionBundleFactory {
  @Autowired
  private QuestionBundleService questionBundleService;
  @Autowired
  private TestPaperService testPaperService;

  public QuestionBundle createQuestionBundle(int year, int month, QuestionBundleTimeZone timeZone, QuestionBundlePaper paper) {
    QuestionBundle questionBundle = QuestionBundle.createQuestionBundle(year, month, timeZone, paper);
    questionBundleService.add(questionBundle);
    return questionBundle;
  }

  public QuestionBundle findOne(Long id) {
    QuestionBundle questionBundle = questionBundleService.findOneWithQuestion(id);
    List<TestPaperQuestionBundle> testPaperQuestionBundles = questionBundle.getTestPaperQuestionBundles();
    testPaperQuestionBundles.forEach(testPaperQuestionBundle -> testPaperService.findOneWithQuestionBundle(testPaperQuestionBundle.getTestPaper().getId()));
    return questionBundle;
  }
}
