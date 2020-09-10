package com.example.que_bang.question_bundle.form;

import com.example.que_bang.domain.question_bundle.QuestionBundlePaper;
import com.example.que_bang.domain.question_bundle.QuestionBundleTimeZone;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

@Data
public class QuestionBundleForm {
  @NotNull
  @PastOrPresent
  private java.time.Year year;
  @NotNull
//  @Pattern(regexp = "^5$|^11$", message = "month는 5와 11만 가능합니다.")
  private Integer month;
  @NotNull
  private QuestionBundleTimeZone timeZone;
  @NotNull
  private QuestionBundlePaper paper; // 시험 종류
}
