package com.example.que_bang.question.form;

import com.example.que_bang.domain.Topic;
import com.example.que_bang.domain.question.QuestionType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionForm {
  @NotNull
  private QuestionType type;
  @NotNull
  private Double score;
  private List<Topic> topics = new ArrayList<>();
  private String content;
  private String answerContent;
}
