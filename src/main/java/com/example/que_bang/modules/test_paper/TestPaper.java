package com.example.que_bang.modules.test_paper;

import com.example.que_bang.modules.common.BaseTimeEntity;
import com.example.que_bang.modules.common.Weight;
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
public class TestPaper extends BaseTimeEntity {
  @Id
  @GeneratedValue
  @Column(name = "test_paper_id")
  private Long id;
  private String title;
  @NotNull
  @Enumerated(EnumType.STRING)
  private TestPaperStatus status; // READY, COMP (진행중, 완료됨)

  @OneToMany(mappedBy = "testPaper")
  @OrderBy("weight ASC")
  private List<TestPaperQuestionBundle> testPaperQuestionBundles = new ArrayList<>();

  public static TestPaper createTestPaper(String title, @NotNull TestPaperStatus status) {
    TestPaper testPaper = new TestPaper();
    testPaper.title = title;
    testPaper.status = status;
    return testPaper;
  }

  public static TestPaper createTestPaperWithTestPaperQuestionBundles(String title, @NotNull TestPaperStatus status, TestPaperQuestionBundle... testPaperQuestionBundles) {
    TestPaper testPaper = createTestPaper(title, status);
    for (TestPaperQuestionBundle testPaperQuestionBundle : testPaperQuestionBundles) {
      testPaper.addTestPaperQuestionBundle(testPaperQuestionBundle);
    }
    return testPaper;
  }

  public void addTestPaperQuestionBundle(TestPaperQuestionBundle testPaperQuestionBundle) {
    testPaperQuestionBundle.getWeight().setValue(this.generateMinWeight());
    this.testPaperQuestionBundles.add(testPaperQuestionBundle);
    testPaperQuestionBundle.setTestPaper(this);
  }

  public Double generateMinWeight() {
    Optional<Double> min = testPaperQuestionBundles.stream().map(question -> question.getWeight().getValue()).min(Double::compareTo);
    return min.map(aDouble -> aDouble - Weight.weightInterval).orElse(Weight.defaultWeight);
  }
}
