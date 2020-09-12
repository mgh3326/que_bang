package com.example.que_bang.domain.question;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum QuestionType {
  ESSAY("Essay"), MULTIPLE_CHOICE("Multiple choice"), SHORT_ANSWER("Short answer");
  private final String displayValue;
}
