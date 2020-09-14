package com.example.que_bang.repository;


import com.example.que_bang.domain.Question;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository<T extends Question> extends CrudRepository<T, Long> {
  @EntityGraph(value = "Question.withQuestionBundle", type = EntityGraph.EntityGraphType.LOAD)
  Optional<Question> findWithQuestionBundleById(Long id);

}
