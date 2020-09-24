package com.example.que_bang.modules.test_paper;

import com.example.que_bang.modules.question_bundle.QuestionBundle;
import com.example.que_bang.modules.question_bundle.QuestionBundleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
@Transactional
public class TestPaperFactory {
  @Autowired
  private TestPaperService testPaperService;
  @Autowired
  private QuestionBundleService questionBundleService;

  public TestPaper createTestPaper(String tittle) {
    TestPaper testPaper = TestPaper.createTestPaper(tittle);
    testPaperService.add(testPaper);
    return testPaper;
  }

  public TestPaper createTestPaperWithAddQuestionBundle(String tittle, Long... questionBundleIdList) {
    TestPaper testPaper = createTestPaper(tittle);
    for (Long questionBundleId : questionBundleIdList) {
      testPaperService.addQuestionBundle(testPaper.getId(), questionBundleId);
    }
    return testPaper;
  }

  public TestPaper findOne(Long id) {
    TestPaper testPaper = testPaperService.findOne(id);
    List<TestPaperQuestionBundle> testPaperQuestionBundles = testPaper.getTestPaperQuestionBundles();
    testPaperQuestionBundles.forEach(testPaperQuestionBundle -> questionBundleService.findOneWithQuestion(testPaperQuestionBundle.getQuestionBundle().getId()));
    return testPaper;
  }
}
