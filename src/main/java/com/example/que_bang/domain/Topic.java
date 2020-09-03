package com.example.que_bang.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
  @Id
  @GeneratedValue
  @Column(name = "topic_id")
  private Long id;

  @Column(unique = true, nullable = false)
  private String title;

  @Builder
  public Topic(String title) {
    this.title = title;
  }
}
