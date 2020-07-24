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
public class Question {
  @Id
  @GeneratedValue
  private Long id;
  private String content;
  @Builder.Default
  @ManyToMany
  private Set<Tag> tags = new HashSet<>();
  @Builder.Default
  @ManyToMany
  private Set<Image> images = new HashSet<>();
  @OneToOne
  @JoinColumn(name = "answer_id")
  private Answer answer;
}