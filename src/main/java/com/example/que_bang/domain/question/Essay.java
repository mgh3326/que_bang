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

  public Essay(String content, Double score, Answer answer, QuestionMainTopic mainTopic, QuestionSubTopic subTopic) {
    super(content, score, answer, mainTopic, subTopic);
  }

  public static Essay createEssayWithAnswerContent(String content, double score, String answerContent, QuestionMainTopic mainTopic, QuestionSubTopic subTopic) {
    return new Essay(content, score, new Answer(answerContent), mainTopic, subTopic);
  }
}
