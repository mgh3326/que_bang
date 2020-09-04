package com.example.que_bang.service;

import com.example.que_bang.domain.Question;
import com.example.que_bang.domain.QuestionBundle;
import com.example.que_bang.repository.QuestionBundleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionBundleService {
  private final QuestionBundleRepository questionBundleRepository;

  @Transactional
  public Long add(QuestionBundle questionBundle) {
    questionBundleRepository.save(questionBundle);
    return questionBundle.getId();
  }

  public QuestionBundle findOne(Long id) {
    return questionBundleRepository.findById(id).orElseThrow();
  }
}
