package com.example.que_bang.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
  @Id
  @GeneratedValue
  @Column(name = "topic_id")
  private Long id;

  @Column(unique = true, nullable = false)
  private String name;

  @ManyToMany
  @JoinTable(name = "category_item",
          joinColumns = @JoinColumn(name = "category_id"),
          inverseJoinColumns = @JoinColumn(name = "question_id"))
  private List<Question> questions = new ArrayList<>();
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "parent_id")
  private Topic parent;
  @OneToMany(mappedBy = "parent")
  private List<Topic> child = new ArrayList<>();

  public void addChildTopic(Topic child) {
    this.child.add(child);
    child.setParent(this);
  }

  public Topic(String name) {
    this.name = name;
  }

  public static Topic createTopic(String name) {
    return new Topic(name);
  }
}
