package com.example.que_bang.modules.test_paper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestPaperFactory {
  @Autowired
  private TestPaperService testPaperService;

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
}
