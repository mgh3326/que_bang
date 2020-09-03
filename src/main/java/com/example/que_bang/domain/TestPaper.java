package com.example.que_bang.domain;

import com.example.que_bang.domain.test_paper.TestPaperQuestionBundle;
import com.example.que_bang.domain.test_paper.TestPaperStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
public class TestPaper {
  @Id
  @GeneratedValue
  @Column(name = "test_paper_id")
  private Long id;
  private String title;
  @NotNull
  @Enumerated(EnumType.STRING)
  private TestPaperStatus status; // READY, COMP (진행중, 완료됨)

  @OneToMany(mappedBy = "testPaper")
  private List<TestPaperQuestionBundle> testPaperQuestionBundles = new ArrayList<>();
}
