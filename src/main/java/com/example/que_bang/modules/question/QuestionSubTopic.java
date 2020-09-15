package com.example.que_bang.modules.question;


public enum QuestionSubTopic {
  S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, S11;

  public int getDisplayValue() {
    return ordinal() + 1;
  }
}
