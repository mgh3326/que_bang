package com.example.que_bang.modules.question;

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
public class ShortAnswer extends Question {
  public ShortAnswer(String content, Double weight, String answerContent, QuestionMainTopic mainTopic, QuestionSubTopic subTopic) {
    super(content, weight, answerContent, mainTopic, subTopic);
  }

  public static ShortAnswer createShortAnswerWithAnswerContent(String content, double weight, String answerContent, QuestionMainTopic mainTopic, QuestionSubTopic subTopic) {
    return new ShortAnswer(content, weight, answerContent, mainTopic, subTopic);
  }
}
