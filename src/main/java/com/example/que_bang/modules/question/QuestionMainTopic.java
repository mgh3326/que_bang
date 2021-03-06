package com.example.que_bang.modules.question;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

import static com.example.que_bang.modules.question.QuestionSubTopic.*;

@Getter
@RequiredArgsConstructor
public enum QuestionMainTopic {
  M1(Arrays.asList(S1, S2, S3, S4, S5, S6)),
  M2(Arrays.asList(S1, S2, S3, S4, S5, S6, S7, S8, S9)),
  M3(Arrays.asList(S1, S2, S3, S4, S5)),
  M4(Arrays.asList(S1, S2, S3, S4)),
  M5(Arrays.asList(S1, S2, S3, S4)),
  M6(Arrays.asList(S1, S2, S3, S4, S5, S6)),
  M7(Arrays.asList(S1, S2, S3)),
  M8(Arrays.asList(S1, S2, S3)),
  M9(Arrays.asList(S1, S2, S3, S4)),
  M10(Arrays.asList(S1, S2, S3)),
  M11(Arrays.asList(S1, S2, S3, S4));
  private final List<QuestionSubTopic> subTopics;

  public int getDisplayValue() {
    return ordinal() + 1;
  }
}
