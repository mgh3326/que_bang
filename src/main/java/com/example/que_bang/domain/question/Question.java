package com.example.que_bang.domain.question;

import com.example.que_bang.domain.Answer;
import com.example.que_bang.domain.Image;
import com.example.que_bang.domain.Tag;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public abstract class Question {
  @Id
  @GeneratedValue
  @Column(name = "question_id")
  private Long id;
  private String content;
  private Double score;
  private Double weight;
  @ManyToMany
  private Set<Tag> tags = new HashSet<>();
  @ManyToMany
  private Set<Image> images = new HashSet<>();
  @Embedded
  private Answer answer;

  public Question(String content, Double score, Double weight, Answer answer) {
    this.content = content;
    this.score = score;
    this.weight = weight;
    this.answer = answer;
  }

  public Question() {

  }
}
