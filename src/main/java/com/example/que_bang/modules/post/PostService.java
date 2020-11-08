package com.example.que_bang.modules.post;

import com.example.que_bang.modules.question_bundle.QuestionBundle;
import com.example.que_bang.modules.question_bundle.QuestionBundleRepository;
import com.example.que_bang.modules.question_bundle.query.QuestionBundleQueryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
  private final PostRepository postRepository;
  private final ModelMapper modelMapper;

  @Transactional
  public Long add(Post post) {
    postRepository.save(post);
    return post.getId();
  }

  public Post findOne(Long id) {
    return postRepository.findById(id).orElseThrow();
  }

  public List<Post> findAll() {
    return postRepository.findAllByOrderByDateAsc();
  }

  @Transactional
  public void updatePost(Long id, PostForm postForm) {
    Post post = findOne(id);
    modelMapper.map(postForm, post);
  }

  @Transactional
  public void deleteOne(Long id) {
    Post post = findOne(id);
    postRepository.delete(post);
  }
}
