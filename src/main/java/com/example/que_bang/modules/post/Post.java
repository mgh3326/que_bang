package com.example.que_bang.modules.post;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = {@Index(columnList = "date")})
public class Post {
  @Id
  @GeneratedValue
  @Column(name = "post_id")
  private Long id;
  @Lob
  @Basic(fetch = FetchType.LAZY)
  private String content;
  private String type;
  private String title;
  private LocalDate date;

  public static Post createPost(String content, String type, String title, LocalDate date) {
    Post post = new Post();
    post.content = content;
    post.type = type;
    post.title = title;
    post.date = date;
    return post;
  }
}
