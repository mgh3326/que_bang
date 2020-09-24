package com.example.que_bang.modules.test_paper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TestPaperRepository extends JpaRepository<TestPaper, Long> {

  @Transactional
  @Modifying
  @Query("delete from TestPaper t where t.id in (select t2.id from TestPaper t2)")
  void deleteAllByIdInQuery();
}
