package com.example.que_bang.modules.test_paper;

import com.example.que_bang.modules.common.BaseTimeEntity;
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
public class TestPaper extends BaseTimeEntity {
  @Id
  @GeneratedValue
  @Column(name = "test_paper_id")
  private Long id;
  private String title;
  @NotNull
  @Enumerated(EnumType.STRING)
  private TestPaperStatus status = TestPaperStatus.READY; // READY, COMP (진행중, 완료됨)

  @OneToMany(mappedBy = "testPaper", cascade = CascadeType.ALL)
  @OrderBy("weight ASC")
  private List<TestPaperQuestionBundle> testPaperQuestionBundles = new ArrayList<>();

  public static TestPaper createTestPaper(String title) {
    TestPaper testPaper = new TestPaper();
    testPaper.title = title;
    return testPaper;
  }

  public static TestPaper createTestPaperWithTestPaperQuestionBundles(String title, TestPaperQuestionBundle... testPaperQuestionBundles) {
    TestPaper testPaper = createTestPaper(title);
    for (TestPaperQuestionBundle testPaperQuestionBundle : testPaperQuestionBundles) {
      testPaper.addTestPaperQuestionBundle(testPaperQuestionBundle);
    }
    return testPaper;
  }

  public void addTestPaperQuestionBundle(TestPaperQuestionBundle testPaperQuestionBundle) {
    testPaperQuestionBundle.setWeight(this.generateMinWeight());
    this.testPaperQuestionBundles.add(testPaperQuestionBundle);
    testPaperQuestionBundle.setTestPaper(this);
  }

  public Double generateMinWeight() {
    Optional<Double> min = testPaperQuestionBundles.stream().map(TestPaperQuestionBundle::getWeight).min(Double::compareTo);
    return min.map(aDouble -> aDouble - weightInterval).orElse(defaultWeight);
  }
}
