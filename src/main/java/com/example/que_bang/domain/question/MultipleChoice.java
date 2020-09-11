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
  public MultipleChoice(String content, Double weight, Answer answer) {
    super(content, weight, answer);
  }

  public static MultipleChoice createMultipleChoiceWithAnswerContent(String content, double weight, String answerContent) {
    return new MultipleChoice(content, weight, new Answer(answerContent));
  }
}
