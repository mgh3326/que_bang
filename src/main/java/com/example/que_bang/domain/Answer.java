package com.example.que_bang.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
  @Id
  @GeneratedValue
  private Long id;
  private String content;
  @ManyToMany
  private Set<Image> images = new HashSet<>();
  @OneToOne
  private Question question;
}
