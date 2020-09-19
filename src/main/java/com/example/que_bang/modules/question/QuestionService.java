package com.example.que_bang.modules.question;

import com.example.que_bang.modules.question.form.QuestionForm;
import com.example.que_bang.modules.question_bundle.QuestionBundle;
import com.example.que_bang.modules.question_bundle.QuestionBundleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionService {
  @Autowired
  private QuestionRepository<Question> questionRepository;
  private final ModelMapper modelMapper;
  private final QuestionBundleService questionBundleService;

  @Transactional
  public Long add(Question question) {
    questionRepository.save(question);

    return question.getId();
  }

  @Transactional
  public Long addForm(QuestionForm questionForm) {
    Question question = formMapper(questionForm);
    questionRepository.save(question);

    return question.getId();
  }

  public Question findOne(Long id) {
    return questionRepository.findById(id).orElseThrow();
  }

  public Question findOneWithQuestionBundle(Long id) {
    return questionRepository.findWithQuestionBundleById(id).orElseThrow();
  }

  public Question formMapper(QuestionForm questionForm) {

    Question question = getQuestion(questionForm);
    question.setContent(questionForm.getContent());
    question.setScore(questionForm.getScore());
    question.setMainTopic(questionForm.getMainTopic());
    question.setSubTopic(questionForm.getSubTopic());
    question.setAnswer(Answer.createAnswer(questionForm.getAnswerContent()));
    QuestionBundle questionBundle = questionBundleService.findOne(questionForm.getQuestionBundleId());
    questionBundle.addQuestion(question);
    return question;
  }

  public List<Question> findAllByQuestionBundleId(Long questionBundleId) {
    return questionRepository.findAllByQuestionBundleId(questionBundleId);
  }

  private Question getQuestion(QuestionForm questionForm) {
    switch (questionForm.getType()) {
      case ESSAY:
        return modelMapper.map(this, Essay.class);
      case MULTIPLE_CHOICE:
        return modelMapper.map(this, MultipleChoice.class);
      case SHORT_ANSWER:
        return modelMapper.map(this, ShortAnswer.class);
      default:
        throw new IllegalStateException("Unexpected value: " + questionForm.getType());
    }
  }

}
