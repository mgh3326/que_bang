package com.example.que_bang.modules.question;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum QuestionType {
  E("Essay"), M("Multiple choice"), S("Short answer");
  private final String displayValue;
}
