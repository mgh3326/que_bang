package com.example.que_bang.domain.question;

import com.example.que_bang.domain.Answer;
import com.example.que_bang.domain.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
@Getter
@Setter
@NoArgsConstructor
public class MultipleChoice extends Question {
  public MultipleChoice(String content, Double score, Double weight, Answer answer) {
    super(content, score, weight, answer);
  }

  public static MultipleChoice createMultipleChoice(String content, Double score, Double weight, Answer answer) {
    return new MultipleChoice(content, score, weight, answer);
  }
}
