package com.example.que_bang.service;

import com.example.que_bang.domain.Question;
import com.example.que_bang.domain.Topic;
import com.example.que_bang.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionService {
  @Autowired
  private QuestionRepository<Question> questionRepository;

  @Transactional
  public Long add(Question question) {
    questionRepository.save(question);

    return question.getId();
  }

  public void addTag(Question question, Topic topic) {
    Optional<Question> byId = questionRepository.findById(question.getId());
    byId.ifPresent(a -> a.getTopics().add(topic));
  }

  public List<Topic> getTopics(Question question) {
    Optional<Question> byId = questionRepository.findById(question.getId());
    return byId.orElseThrow().getTopics();
  }

  public void removeTopic(Question question, Topic topic) {
    Optional<Question> byId = questionRepository.findById(question.getId());
    byId.ifPresent(a -> a.getTopics().remove(topic));
  }

  public Question findOne(Long id) {
    return questionRepository.findById(id).orElseThrow();
  }
}
