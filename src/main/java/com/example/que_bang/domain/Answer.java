package com.example.que_bang.domain;

import lombok.*;

import javax.persistence.*;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {
  @Column(name = "answer_content")
  @Lob
  @Basic(fetch = FetchType.EAGER)
  private String content;

  public static Answer createAnswer(String content) {
    return new Answer(content);
  }
}
