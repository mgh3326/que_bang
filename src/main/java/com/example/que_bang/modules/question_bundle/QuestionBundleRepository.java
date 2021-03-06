package com.example.que_bang.modules.question_bundle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface QuestionBundleRepository extends JpaRepository<QuestionBundle, Long> {
  @Transactional
  @Modifying
  @Query("delete from QuestionBundle q where q.id in (select q2.id from QuestionBundle q2)")
  void deleteAllByIdInQuery();
}
