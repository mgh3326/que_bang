package com.example.que_bang.service;

import com.example.que_bang.domain.Tag;
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
  public Long add(Tag tag) {
    tagRepository.save(tag);

    return tag.getId();
  }

  public Tag findOne(Long id) {
    return tagRepository.findById(id).orElseThrow();
  }
}
