package com.example.que_bang.domain;

import com.example.que_bang.domain.question.Essay;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
@NoArgsConstructor
public abstract class Question {
  @Id
  @GeneratedValue
  @Column(name = "question_id")
  private Long id;
  @Lob
  private String content;
  private Double score;
  private Double weight;
  @ManyToMany(mappedBy = "questions")
  private List<Topic> topics = new ArrayList<>();

  @ManyToMany
  private Set<Image> images = new HashSet<>();
  @Embedded
  private Answer answer;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "question_bundle_id")
  private QuestionBundle questionBundle;

  public Question(String content, Double score, Double weight, Answer answer) {
    this.content = content;
    this.score = score;
    this.weight = weight;
    this.answer = answer;
  }
}
