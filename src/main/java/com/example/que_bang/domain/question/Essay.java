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

  public Essay(String content, Double score, Double weight, Answer answer) {
    super(content, score, weight, answer);
  }

  public static Essay createEssayWithAnswerContent(String content, double score, double weight, String answer_content) {
    return new Essay(content, score, weight, new Answer(answer_content));
  }
}
