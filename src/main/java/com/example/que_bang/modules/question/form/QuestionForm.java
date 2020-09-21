package com.example.que_bang.modules.question.form;

import com.example.que_bang.modules.question.QuestionMainTopic;
import com.example.que_bang.modules.question.QuestionSubTopic;
import com.example.que_bang.modules.question.QuestionType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class QuestionForm {
  @NotNull
  private QuestionType type;
  @NotNull
  private Double score;//TODO null이 넘어 가는 경우가 있는걸로 보인다.
  private QuestionMainTopic mainTopic;
  private QuestionSubTopic subTopic;
  private String content;
  private String answerContent;
  @NotNull
  private Long questionBundleId;
}
