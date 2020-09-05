package com.example.que_bang.service;

import com.example.que_bang.domain.QuestionBundle;
import com.example.que_bang.domain.TestPaper;
import com.example.que_bang.repository.QuestionBundleRepository;
import com.example.que_bang.repository.TestPaperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TestPaperService {
  private final TestPaperRepository testPaperRepository;

  @Transactional
  public Long add(TestPaper testPaper) {
    testPaperRepository.save(testPaper);
    return testPaper.getId();
  }

  public TestPaper findOne(Long id) {
    return testPaperRepository.findById(id).orElseThrow();
  }
}
