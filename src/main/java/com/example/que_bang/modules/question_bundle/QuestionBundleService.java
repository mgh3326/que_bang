package com.example.que_bang.modules.question_bundle;

import com.example.que_bang.modules.question_bundle.form.QuestionBundleForm;
import com.example.que_bang.modules.question_bundle.query.QuestionBundleFlatDto;
import com.example.que_bang.modules.question_bundle.query.QuestionBundleQueryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionBundleService {
  private final QuestionBundleRepository questionBundleRepository;
  private final QuestionBundleQueryRepository questionBundleQueryRepository;
  private final ModelMapper modelMapper;

  @Transactional
  public Long add(QuestionBundle questionBundle) {
    questionBundleRepository.save(questionBundle);
    return questionBundle.getId();
  }

  public QuestionBundle findOne(Long id) {
    return questionBundleRepository.findById(id).orElseThrow();
  }

  public QuestionBundle findOneWithQuestion(Long id) {
    return questionBundleQueryRepository.findOneWithQuestion(id).orElseThrow();
  }

  @Transactional
  public void updateQuestionBundleDescription(Long questionBundleId, QuestionBundleForm questionBundleForm) {
    QuestionBundle questionBundle = findOne(questionBundleId);
    modelMapper.map(questionBundleForm, questionBundle);
  }

  public List<QuestionBundleFlatDto> findAll() {
    return questionBundleQueryRepository.findAll();
  }
}
