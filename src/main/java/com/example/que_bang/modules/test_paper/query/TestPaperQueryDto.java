package com.example.que_bang.modules.test_paper.query;

import com.example.que_bang.modules.question_bundle.QuestionBundle;
import com.example.que_bang.modules.test_paper.TestPaper;
import com.example.que_bang.modules.test_paper.TestPaperQuestionBundle;
import com.example.que_bang.modules.test_paper.TestPaperStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestPaperQueryDto {
  public Long id;
  public String title;
  public TestPaperStatus status;
  public Long questionBundleCount;
  public Long questionCount;
  public List<QuestionBundle> questionBundles;

  public String getName() {
    {
      return String.format("%s(%d)", title, questionBundleCount);
    }
  }

  public TestPaperQueryDto(TestPaper testPaper, Long questionBundleCount, Long questionCount) {
    this.id = testPaper.getId();
    this.title = testPaper.getTitle();
    this.status = testPaper.getStatus();
    this.questionBundleCount = questionBundleCount;
    this.questionBundles = testPaper.getTestPaperQuestionBundles().stream().map(TestPaperQuestionBundle::getQuestionBundle).collect(Collectors.toList());
    this.questionCount = questionCount;
  }
}
