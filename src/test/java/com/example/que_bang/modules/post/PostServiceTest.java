package com.example.que_bang.modules.post;

import com.example.que_bang.modules.common.BaseServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
class PostServiceTest extends BaseServiceTest {
  @Autowired
  PostService postService;

  @Test
  void add() {
    Post post = Post.createPost("content", "기념일", "test", LocalDate.parse("2020-06-21"));
    postService.add(post);
    Post one = postService.findOne(post.getId());
    assertEquals(one.getContent(), post.getContent());
    assertEquals(one.getType(), post.getType());
    assertEquals(one.getTitle(), post.getTitle());
    assertEquals(one.getDate(), post.getDate());
  }
}
