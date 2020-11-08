package com.example.que_bang.modules.post;

import com.example.que_bang.modules.question_bundle.QuestionBundle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
  List<Post> findAllByOrderByDateAsc();
}
