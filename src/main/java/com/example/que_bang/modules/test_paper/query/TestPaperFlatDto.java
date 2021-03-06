package com.example.que_bang.modules.test_paper.query;

import com.example.que_bang.modules.test_paper.TestPaper;
import com.example.que_bang.modules.test_paper.TestPaperStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestPaperFlatDto {
  public Long id;
  public String title;
  public TestPaperStatus status;
  public Long questionBundleCount;

  public String getName() {
    {
      return String.format("%s(%d)", title, questionBundleCount);
    }
  }

  public TestPaperFlatDto(TestPaper testPaper, Long questionBundleCount) {
    this.id = testPaper.getId();
    this.title = testPaper.getTitle();
    this.status = testPaper.getStatus();
    this.questionBundleCount = questionBundleCount;
  }
}
