package com.example.que_bang.domain.question_bundle;


public enum QuestionBundlePaper {
  P1, P2, P3;

  public int getDisplayValue() {
    return ordinal() + 1;
  }
}
