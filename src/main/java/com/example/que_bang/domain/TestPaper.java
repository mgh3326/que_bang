package com.example.que_bang.domain;

import com.example.que_bang.domain.question.Question;

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
  private TestPaperStatus testPaperStatus; // READY, COMP (진행중, 완료됨)

  @OneToMany(mappedBy = "testPaper")
  private List<TestPaperQuestionBundle> testPaperQuestionBundles = new ArrayList<>();
}
