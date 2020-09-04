package com.example.que_bang.domain.question;

import com.example.que_bang.domain.Answer;
import com.example.que_bang.domain.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("S")

@Getter
@Setter
@NoArgsConstructor
public class Short extends Question {
  public Short(String content, Double score, Double weight, Answer answer) {
    super(content, score, weight, answer);
  }

  public static Short createShort(String content, Double score, Double weight, Answer answer) {
    return new Short(content, score, weight, answer);
  }
}

