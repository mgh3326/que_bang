package com.example.que_bang.domain.test_paper;

import com.example.que_bang.domain.BaseTimeEntity;
import com.example.que_bang.domain.QuestionBundle;
import com.example.que_bang.domain.TestPaper;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class TestPaperQuestionBundle extends BaseTimeEntity {
  @Id
  @GeneratedValue
  @Column(name = "test_paper_question_bundle_id")
  private Long id;
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "test_paper_id")
  private TestPaper testPaper;
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "question_bundle_id")
  private QuestionBundle questionBundle;

  public static TestPaperQuestionBundle createWithQuestionBundle(QuestionBundle questionBundle) {
    TestPaperQuestionBundle testPaperQuestionBundle = new TestPaperQuestionBundle();
    testPaperQuestionBundle.questionBundle = questionBundle;
    return testPaperQuestionBundle;
  }
}
