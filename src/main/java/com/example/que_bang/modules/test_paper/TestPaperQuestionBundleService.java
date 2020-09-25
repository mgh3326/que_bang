package com.example.que_bang.modules.test_paper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TestPaperQuestionBundleService {
  private final TestPaperQuestionBundleRepository testPaperQuestionBundleRepository;
  private final ModelMapper modelMapper;

  @Transactional
  public void deleteTestPaperQuestionBundle(Long id) {
    testPaperQuestionBundleRepository.deleteById(id);
  }

  public TestPaperQuestionBundle findOneTestPaperQuestionBundle(Long id) {
    return testPaperQuestionBundleRepository.findById(id).orElseThrow();
  }
}
