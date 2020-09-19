package com.example.que_bang.modules.question_bundle.query;

import com.example.que_bang.modules.question_bundle.QuestionBundle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionBundleFlatDto {
  public Long id;
  public String title;
  public Long questionCount;
  public LocalDateTime createdDate;
  public LocalDateTime lastModifiedDate;

  public String getName() {
    {
      return String.format("%s(%d)", title, questionCount);
    }
  }

  public QuestionBundleFlatDto(QuestionBundle questionBundle, Long questionCount) {
    this.id = questionBundle.getId();
    this.title = questionBundle.getTitle();
    this.createdDate = questionBundle.getCreatedDate();
    this.lastModifiedDate = questionBundle.getLastModifiedDate();
    this.questionCount = questionCount;
  }
}
