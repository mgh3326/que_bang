package com.example.que_bang.modules.test_paper;

import com.example.que_bang.modules.account.AccountRepository;
import com.example.que_bang.modules.account.WithAccount;
import com.example.que_bang.modules.common.BaseControllerTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RequiredArgsConstructor
class TestPaperControllerTest extends BaseControllerTest {
  @Autowired
  protected MockMvc mockMvc;
  @Autowired
  protected TestPaperService testPaperService;
  @Autowired
  private AccountRepository accountRepository;



  @Test
  @WithAccount("robin")
  @DisplayName("문제지 개설 폼 조회")
  void createTestPaperForm() throws Exception {
    mockMvc.perform(get("/new-test_paper"))
            .andExpect(status().isOk())
            .andExpect(view().name("test_paper/form"))
            .andExpect(model().attributeExists("account"))
            .andExpect(model().attributeExists("testPaperForm"));
  }

  @Test
  @WithAccount("robin")
  @DisplayName("문제지 개설 - 완료")
  void createTestPaper_success() throws Exception {
    mockMvc.perform(post("/new-test_paper")
            .param("title", "2020/08/12 문제")
            .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrlPattern("/test_paper/*"));
  }

  @Test
  @WithAccount("robin")
  @DisplayName("문제 묶음 개설 폼 조회")
  void viewTestPaper() throws Exception {
    TestPaper testPaper = TestPaper.createTestPaper("Title");
    testPaperService.add(testPaper);
    mockMvc.perform(get(String.format("/test_paper/%d", testPaper.getId())))
            .andExpect(status().isOk())
            .andExpect(view().name("test_paper/view"))
            .andExpect(model().attributeExists("account"))
            .andExpect(model().attributeExists("testPaper"));
  }
}