package com.example.que_bang.domain;

import com.example.que_bang.domain.question_bundle.QuestionBundlePaper;
import com.example.que_bang.domain.question_bundle.QuestionBundleTimeZone;
import com.example.que_bang.domain.test_paper.TestPaperQuestionBundle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;


@Entity
public class QuestionBundle {
  @Id
  @GeneratedValue
  @Column(name = "question_bundle_id")
  private Long id;
  @NotNull
  private int year;
  @NotNull
  private int month; // 5, 11
  @NotNull
  @Enumerated(EnumType.STRING)
  private QuestionBundleTimeZone timeZone;
  @NotNull
  private QuestionBundlePaper paper; // 시험 종류


  @OneToMany(mappedBy = "questionBundle")
  @OrderBy("weight ASC")
  private List<Question> questions = new ArrayList<>();

  @OneToMany(mappedBy = "questionBundle")
  private List<TestPaperQuestionBundle> testPaperQuestionBundles = new ArrayList<>();
}
