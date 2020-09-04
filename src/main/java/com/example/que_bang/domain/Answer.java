package com.example.que_bang.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {
  @Column(name = "answer_content")
  private String content;

  public static Answer createAnswer(String content) {
    return new Answer(content);
  }
}
