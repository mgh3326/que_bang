package com.example.que_bang.service;

import com.example.que_bang.domain.Topic;
import com.example.que_bang.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagService {
  private final TagRepository tagRepository;

  @Transactional
  public Long add(Topic topic) {
    tagRepository.save(topic);

    return topic.getId();
  }

  public Topic findOne(Long id) {
    return tagRepository.findById(id).orElseThrow();
  }
}
