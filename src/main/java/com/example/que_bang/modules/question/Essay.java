package com.example.que_bang.modules.question;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("E")
@Getter
@Setter
@NoArgsConstructor
public class Essay extends Question {

  public Essay(String content, Double score, String answerContent, QuestionMainTopic mainTopic, QuestionSubTopic subTopic) {
    super(content, score, answerContent, mainTopic, subTopic);
  }

  public static Essay createEssayWithAnswerContent(String content, double score, String answerContent, QuestionMainTopic mainTopic, QuestionSubTopic subTopic) {
    return new Essay(content, score, answerContent, mainTopic, subTopic);
  }
}
