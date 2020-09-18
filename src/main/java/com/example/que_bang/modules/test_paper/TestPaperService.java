package com.example.que_bang.modules.test_paper;

import com.example.que_bang.modules.question_bundle.QuestionBundle;
import com.example.que_bang.modules.question_bundle.QuestionBundleService;
import com.example.que_bang.modules.test_paper.query.TestPaperFlatDto;
import com.example.que_bang.modules.test_paper.query.TestPaperQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TestPaperService {
  private final TestPaperRepository testPaperRepository;
  private final TestPaperQueryRepository testPaperQueryRepository;
  private final QuestionBundleService questionBundleService;

  @Transactional
  public Long add(TestPaper testPaper) {
    testPaperRepository.save(testPaper);
    return testPaper.getId();
  }

  public TestPaper findOne(Long id) {
    return testPaperRepository.findById(id).orElseThrow();
  }

  public List<TestPaperFlatDto> findAllByStatus(TestPaperStatus status) {
    return testPaperQueryRepository.findAll(status);
  }
  @Transactional
  public void addQuestionBundle(Long testPaperId, Long questionBundleId) {
    TestPaper testPaper = findOne(testPaperId);
    QuestionBundle questionBundle = questionBundleService.findOne(questionBundleId);
    TestPaperQuestionBundle testPaperQuestionBundle = TestPaperQuestionBundle.createWithQuestionBundle(questionBundle);
    testPaper.addTestPaperQuestionBundle(testPaperQuestionBundle);
  }

}
