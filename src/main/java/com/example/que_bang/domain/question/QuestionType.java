package com.example.que_bang.domain.question;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum QuestionType {
  ESSAY, MULTIPLE_CHOICE, SHORT_ANSWER;

  public String getDiscriminatorValue() {
    return name().substring(0, 1);
  }
}
