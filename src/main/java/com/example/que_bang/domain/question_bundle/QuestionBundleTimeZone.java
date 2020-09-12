package com.example.que_bang.domain.question_bundle;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum QuestionBundleTimeZone {
  TZ0("TimeZone 1"), TZ1("TimeZone 2"), TZ2("TimeZone 3");
  private final String displayValue;
  }
