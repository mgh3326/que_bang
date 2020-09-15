package com.example.que_bang.modules.question;

import lombok.*;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
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
