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

  public static MultipleChoice createMultipleChoiceWithAnswerContent(String content, double score, double weight, String answer_content) {
    return new MultipleChoice(content, score, weight, new Answer(answer_content));
  }
}
