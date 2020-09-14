package com.example.que_bang.domain;

import com.example.que_bang.domain.question.QuestionMainTopic;
import com.example.que_bang.domain.question.QuestionSubTopic;
import lombok.*;

import javax.persistence.*;

import static com.example.que_bang.domain.QuestionBundle.defaultWeight;
import static javax.persistence.FetchType.LAZY;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@EqualsAndHashCode(of = "id", callSuper = false)
@Getter
@Setter
@NoArgsConstructor
public abstract class Question extends BaseTimeEntity {
  @Id
  @GeneratedValue
  @Column(name = "question_id")
  private Long id;
  @Lob
  @Basic(fetch = FetchType.EAGER)
  private String content;
  private Double score;
  private Double weight = defaultWeight;
  @Enumerated(EnumType.STRING)
  private QuestionMainTopic mainTopic;
  @Enumerated(EnumType.STRING)
  private QuestionSubTopic subTopic;

  @Embedded
  private Answer answer;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "question_bundle_id")
  private QuestionBundle questionBundle;

  public Question(String content, Double score, Answer answer, QuestionMainTopic mainTopic, QuestionSubTopic subTopic) {
    this.content = content;
    this.score = score;
    this.answer = answer;
    this.mainTopic = mainTopic;
    this.subTopic = subTopic;
  }
}
