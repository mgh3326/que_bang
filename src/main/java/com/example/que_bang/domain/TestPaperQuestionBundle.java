package com.example.que_bang.domain;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class TestPaperQuestionBundle {
  @Id
  @GeneratedValue
  @Column(name = "test_paper_question_bundle_id")
  private Long id;
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "test_paper_id")
  private TestPaper testPaper;
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "question_bundle_id")
  private QuestionBundle questionBundle;
}
