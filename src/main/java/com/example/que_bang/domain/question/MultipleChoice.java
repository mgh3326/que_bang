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
  public MultipleChoice(String content, Double score, Answer answer, QuestionMainTopic mainTopic, QuestionSubTopic subTopic) {
    super(content, score, answer, mainTopic, subTopic);
  }

  public static MultipleChoice createMultipleChoiceWithAnswerContent(String content, double score, String answerContent, QuestionMainTopic mainTopic, QuestionSubTopic subTopic) {
    return new MultipleChoice(content, score, new Answer(answerContent), mainTopic, subTopic);
  }
}
