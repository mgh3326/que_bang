package com.example.que_bang.domain.question;

import com.example.que_bang.domain.Answer;
import com.example.que_bang.domain.Question;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("E")
@Getter
@Setter
@NoArgsConstructor
public class Essay extends Question {

  public Essay(String content, Double weight, Answer answer) {
    super(content, weight, answer);
  }

  public static Essay createEssayWithAnswerContent(String content, double weight, String answerContent) {
    return new Essay(content, weight, new Answer(answerContent));
  }
}
