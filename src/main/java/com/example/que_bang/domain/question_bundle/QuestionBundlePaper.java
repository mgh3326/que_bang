package com.example.que_bang.domain.question_bundle;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum QuestionBundlePaper {
  P1("Paper 1"), P2("Paper 2"), P3("Paper 3");
  private final String displayValue;
}
