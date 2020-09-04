package com.example.que_bang.service;

import com.example.que_bang.domain.Topic;
import com.example.que_bang.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TopicService {
  private final TopicRepository topicRepository;

  @Transactional
  public Long add(Topic topic) {
    topicRepository.save(topic);

    return topic.getId();
  }

  public Topic findOne(Long id) {
    return topicRepository.findById(id).orElseThrow();
  }
}
