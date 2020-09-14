package com.example.que_bang.domain;

import com.example.que_bang.domain.question_bundle.QuestionBundlePaper;
import com.example.que_bang.domain.question_bundle.QuestionBundleTimeZone;
import com.example.que_bang.domain.test_paper.TestPaperQuestionBundle;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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


  @OneToMany(mappedBy = "questionBundle")
  @OrderBy("weight ASC")
  private List<Question> questions = new ArrayList<>();

  @OneToMany(mappedBy = "questionBundle")
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
    question.setWeight(this.generateMinQuestionScore());
    this.questions.add(question);
    question.setQuestionBundle(this);
  }

  public final static Double defaultQuestionWeight = 1.0;
  public final static Double questionWeightInterval = 0.01;

  public Double generateMinQuestionScore() {
    Optional<Double> min = questions.stream().map(Question::getWeight).min(Double::compareTo);
    return min.map(aDouble -> aDouble - questionWeightInterval).orElse(defaultQuestionWeight);
  }

  @Override
  public String toString() {
    return getName();
  }

  public String getName() {
    return String.format("%d/%d/%s/%s", year, month, timeZone, paper);
  }
}
