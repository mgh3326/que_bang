package com.example.que_bang.modules.question_bundle.query;

import com.example.que_bang.modules.question_bundle.QuestionBundle;
import com.example.que_bang.modules.question_bundle.QuestionBundlePaper;
import com.example.que_bang.modules.question_bundle.QuestionBundleTimeZone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionBundleFlatDto {
  public Long id;
  public int year;
  public int month;
  public QuestionBundleTimeZone timeZone;
  public QuestionBundlePaper paper;
  public Long questionCount;
  public LocalDateTime createdDate;
  public LocalDateTime lastModifiedDate;

  public QuestionBundleFlatDto(QuestionBundle questionBundle, Long questionCount) {
    this.id = questionBundle.getId();
    this.year = questionBundle.getYear();
    this.month = questionBundle.getMonth();
    this.timeZone = questionBundle.getTimeZone();
    this.paper = questionBundle.getPaper();
    this.createdDate = questionBundle.getCreatedDate();
    this.lastModifiedDate = questionBundle.getLastModifiedDate();
    this.questionCount = questionCount;
  }
}
