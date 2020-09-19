package com.example.que_bang.modules.test_paper.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestPaperFlatDto {
  public Long id;
  public String title;
  public Long questionBundleCount;

  public String getName() {
    {
      return String.format("%s(%d)", title, questionBundleCount);
    }
  }
}
