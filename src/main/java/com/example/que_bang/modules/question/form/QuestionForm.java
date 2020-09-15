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
  private Double score;
  private QuestionMainTopic mainTopic;
  private QuestionSubTopic subTopic;
  private String content;
  private String answerContent;
  @NotNull
  private Long questionBundleId;
}
