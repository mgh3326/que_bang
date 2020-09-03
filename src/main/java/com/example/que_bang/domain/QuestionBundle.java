package com.example.que_bang.domain;

import com.example.que_bang.domain.question.Question;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class QuestionBundle {
  @Id
  @GeneratedValue
  @Column(name = "question_bundle_id")
  private Long id;

  @OneToMany(mappedBy = "questionBundle")
  private List<Question> questions = new ArrayList<>();
}
