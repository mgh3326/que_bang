package com.example.que_bang.service;

import com.example.que_bang.domain.Answer;
import com.example.que_bang.domain.Question;
import com.example.que_bang.domain.QuestionBundle;
import com.example.que_bang.domain.question.Essay;
import com.example.que_bang.domain.question.MultipleChoice;
import com.example.que_bang.domain.question.ShortAnswer;
import com.example.que_bang.question.form.QuestionForm;
import com.example.que_bang.question_bundle.QuestionBundleService;
import com.example.que_bang.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

  public Question findOne(Long id) {
    return questionRepository.findById(id).orElseThrow();
  }

  public Question formMapper(QuestionForm questionForm) {

    Question question = getQuestion(questionForm);
    question.setContent(questionForm.getContent());
    question.setScore(questionForm.getScore());
    question.setAnswer(Answer.createAnswer(questionForm.getAnswerContent()));
    QuestionBundle questionBundle = questionBundleService.findOne(questionForm.getQuestionBundleId());
    questionBundle.addQuestion(question);
    return question;
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
