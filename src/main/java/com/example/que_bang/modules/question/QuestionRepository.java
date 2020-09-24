package com.example.que_bang.modules.question;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository<T extends Question> extends CrudRepository<T, Long> {
  @EntityGraph(value = "Question.withQuestionBundle", type = EntityGraph.EntityGraphType.LOAD)
  Optional<Question> findWithQuestionBundleById(Long id);

  List<Question> findAllByQuestionBundleId(Long questionBundleId);

  @Transactional
  @Modifying
  @Query("delete from Question q where q.id in (select q2.id from Question q2)")
  void deleteAllByIdInQuery();
}
