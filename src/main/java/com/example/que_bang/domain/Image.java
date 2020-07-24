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
public class Image {
  @Id
  @GeneratedValue
  private Long id;

  private String title;

  private String url;
}

