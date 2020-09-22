package com.example.que_bang.modules.test_paper;

import com.example.que_bang.modules.question.Question;
import com.example.que_bang.modules.question_bundle.QuestionBundle;
import com.example.que_bang.modules.question_bundle.QuestionBundleService;
import com.example.que_bang.modules.test_paper.form.TestPaperForm;
import com.example.que_bang.modules.test_paper.query.TestPaperFlatDto;
import com.example.que_bang.modules.test_paper.query.TestPaperQueryDto;
import com.example.que_bang.modules.test_paper.query.TestPaperQueryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
  private final ModelMapper modelMapper;

  @Transactional
  public Long add(TestPaper testPaper) {
    testPaperRepository.save(testPaper);
    return testPaper.getId();
  }

  public TestPaper findOne(Long id) {
    return testPaperRepository.findById(id).orElseThrow();
  }

  public TestPaperQueryDto findOneWithQuestionBundle(Long id) {
    return testPaperQueryRepository.findAllWithQuestionBundle(id).orElseThrow();
  }

  public List<TestPaperFlatDto> findAllByStatus(TestPaperStatus status, Long ignoreQuestionBundleId, Long questionBundleId) {
    return testPaperQueryRepository.findAll(status, ignoreQuestionBundleId, questionBundleId);
  }

  @Transactional
  public void addQuestionBundle(Long testPaperId, Long questionBundleId) {
    TestPaper testPaper = findOne(testPaperId);
    QuestionBundle questionBundle = questionBundleService.findOne(questionBundleId);
    TestPaperQuestionBundle testPaperQuestionBundle = TestPaperQuestionBundle.createWithQuestionBundle(questionBundle);
    testPaper.addTestPaperQuestionBundle(testPaperQuestionBundle);
  }

  @Transactional
  public void updateFromForm(Long id, TestPaperForm testPaperForm) {
    TestPaper testPaper = findOne(id);
    modelMapper.map(testPaperForm, testPaper);
  }

  public List<Question> getQuestions(Long id) {
    return testPaperQueryRepository.getQuestions(id);
  }

  public void deleteOne(Long id) {
    testPaperRepository.deleteById(id);
  }
}
