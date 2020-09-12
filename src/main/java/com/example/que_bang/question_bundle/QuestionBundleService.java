package com.example.que_bang.question_bundle;

import com.example.que_bang.domain.QuestionBundle;
import com.example.que_bang.question_bundle.form.QuestionBundleForm;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionBundleService {
  private final QuestionBundleRepository questionBundleRepository;
  private final ModelMapper modelMapper;

  @Transactional
  public Long add(QuestionBundle questionBundle) {
    questionBundleRepository.save(questionBundle);
    return questionBundle.getId();
  }

  public QuestionBundle findOne(Long id) {
    return questionBundleRepository.findById(id).orElseThrow();
  }

  @Transactional
  public void updateQuestionBundleDescription(Long questionBundleId, QuestionBundleForm questionBundleForm) {
    QuestionBundle questionBundle = findOne(questionBundleId);
    modelMapper.map(questionBundleForm, questionBundle);
  }
}
