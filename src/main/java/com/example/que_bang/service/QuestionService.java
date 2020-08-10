package com.example.que_bang.service;

import com.example.que_bang.domain.Image;
import com.example.que_bang.domain.Tag;
import com.example.que_bang.domain.question.Question;
import com.example.que_bang.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

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

  public void addTag(Question question, Tag tag) {
    Optional<Question> byId = questionRepository.findById(question.getId());
    byId.ifPresent(a -> a.getTags().add(tag));
  }

  public Set<Tag> getTags(Question question) {
    Optional<Question> byId = questionRepository.findById(question.getId());
    return byId.orElseThrow().getTags();
  }

  public void removeTag(Question question, Tag tag) {
    Optional<Question> byId = questionRepository.findById(question.getId());
    byId.ifPresent(a -> a.getTags().remove(tag));
  }

  public void addImage(Question question, Image image) {
    Optional<Question> byId = questionRepository.findById(question.getId());
    byId.ifPresent(a -> a.getImages().add(image));
  }

  public Set<Image> getImages(Question question) {
    Optional<Question> byId = questionRepository.findById(question.getId());
    return byId.orElseThrow().getImages();
  }

  public void removeImage(Question question, Image image) {
    Optional<Question> byId = questionRepository.findById(question.getId());
    byId.ifPresent(a -> a.getImages().remove(image));
  }

  public Question findOne(Long id) {
    return questionRepository.findById(id).orElseThrow();
  }
}
