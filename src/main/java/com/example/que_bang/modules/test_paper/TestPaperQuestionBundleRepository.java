package com.example.que_bang.modules.test_paper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TestPaperQuestionBundleRepository extends JpaRepository<TestPaperQuestionBundle, Long> {

  @Transactional
  @Modifying
  @Query("delete from TestPaperQuestionBundle tq where tq.id in (select tq2.id from TestPaperQuestionBundle tq2)")
  void deleteAllByIdInQuery();
}
