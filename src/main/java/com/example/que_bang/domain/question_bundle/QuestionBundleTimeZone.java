package com.example.que_bang.domain.question_bundle;

public enum QuestionBundleTimeZone {
  T0, T1, T2;

  public int getDisplayValue() {
    return ordinal();
  }
}
