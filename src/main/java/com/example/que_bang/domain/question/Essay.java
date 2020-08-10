package com.example.que_bang.domain.question;

import com.example.que_bang.domain.Answer;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("E")
@Getter
@Setter
@NoArgsConstructor
public class Essay extends Question {
  public Essay(String content, Double score, Double weight, Answer answer) {
    super(content, score, weight, answer);
  }
}
