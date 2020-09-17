package com.example.que_bang.modules.question;

import com.example.que_bang.modules.common.BaseTimeEntity;
import com.example.que_bang.modules.common.Weight;
import com.example.que_bang.modules.question_bundle.QuestionBundle;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@NamedEntityGraph(
        name = "Question.withQuestionBundle",
        attributeNodes = @NamedAttributeNode("questionBundle")
)
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
  @Embedded
  private Weight weight = Weight.createWeight();
  @Enumerated(EnumType.STRING)
  private QuestionMainTopic mainTopic;
  @Enumerated(EnumType.STRING)
  private QuestionSubTopic subTopic;

  @Embedded
  private Answer answer = new Answer();

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "question_bundle_id")
  private QuestionBundle questionBundle;

  public Question(String content, Double score, String answerContent, QuestionMainTopic mainTopic, QuestionSubTopic subTopic) {
    this.content = content;
    this.score = score;
    this.answer.setContent(answerContent);
    this.mainTopic = mainTopic;
    this.subTopic = subTopic;
  }

  public String getTitle() {
    return String.format("%d/%s/%s", id, mainTopic, subTopic);
  }
}
