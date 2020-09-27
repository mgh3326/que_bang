package com.example.que_bang.modules.question_bundle;

import com.example.que_bang.modules.common.BaseTimeEntity;
import com.example.que_bang.modules.question.Question;
import com.example.que_bang.modules.test_paper.TestPaperQuestionBundle;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.que_bang.modules.common.BaseWeightEntity.defaultWeight;
import static com.example.que_bang.modules.common.BaseWeightEntity.weightInterval;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class QuestionBundle extends BaseTimeEntity {
  @Id
  @GeneratedValue
  @Column(name = "question_bundle_id")
  private Long id;
  @NotNull
  private int year;
  @NotNull
  private int month; // 5, 11
  @NotNull
  @Enumerated(EnumType.STRING)
  private QuestionBundleTimeZone timeZone;
  @NotNull
  @Enumerated(EnumType.STRING)
  private QuestionBundlePaper paper; // 시험 종류


  @OneToMany(mappedBy = "questionBundle", cascade = CascadeType.PERSIST, orphanRemoval = true)
  @OrderBy("weight desc")
  private List<Question> questions = new ArrayList<>();

  @OneToMany(mappedBy = "questionBundle", cascade = CascadeType.PERSIST, orphanRemoval = true)
  @OrderBy("weight desc")
  private List<TestPaperQuestionBundle> testPaperQuestionBundles = new ArrayList<>();

  public static QuestionBundle createQuestionBundle(int year, int month, QuestionBundleTimeZone timeZone, QuestionBundlePaper paper) {
    QuestionBundle questionBundle = new QuestionBundle();
    questionBundle.year = year;
    questionBundle.month = month;
    questionBundle.timeZone = timeZone;
    questionBundle.paper = paper;
    return questionBundle;
  }

  public static QuestionBundle createQuestionBundleWithQuestions(int year, int month, QuestionBundleTimeZone timeZone, QuestionBundlePaper paper, Question... questions) {
    QuestionBundle questionBundle = createQuestionBundle(year, month, timeZone, paper);
    for (Question question : questions) {
      questionBundle.addQuestion(question);
    }
    return questionBundle;
  }

  public void addQuestion(Question question) {
    question.setWeight(this.generateMinWeight());
    this.questions.add(question);
    question.setQuestionBundle(this);
  }

  @Override
  public String toString() {
    return getTitle();
  }

  public String getTitle() {
    return String.format("%d/%d/%s/%s", year, month, timeZone, paper);
  }

  private Double generateMinWeight() {
    Optional<Double> min = questions.parallelStream().map(Question::getWeight).min(Double::compareTo);
    return min.map(aDouble -> aDouble - weightInterval).orElse(defaultWeight);
  }
}
