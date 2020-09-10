package com.example.que_bang.question_bundle;

import com.example.que_bang.WithAccount;
import com.example.que_bang.account.AccountRepository;
import com.example.que_bang.common.BaseControllerTest;
import com.example.que_bang.domain.QuestionBundle;
import com.example.que_bang.domain.question_bundle.QuestionBundlePaper;
import com.example.que_bang.domain.question_bundle.QuestionBundleTimeZone;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RequiredArgsConstructor
class QuestionBundleControllerTest extends BaseControllerTest {
  @Autowired
  protected MockMvc mockMvc;
  @Autowired
  protected QuestionBundleService questionBundleService;
  @Autowired
  protected QuestionBundleRepository questionBundleRepository;

  @Test
  @WithAccount("robin")
  @DisplayName("문제 묶음 개설 폼 조회")
  void createQuestionBundleForm() throws Exception {
    mockMvc.perform(get("/new-question_bundle"))
            .andExpect(status().isOk())
            .andExpect(view().name("question_bundle/form"))
            .andExpect(model().attributeExists("account"))
            .andExpect(model().attributeExists("questionBundleForm"));
  }

  @Test
  @WithAccount("robin")
  @DisplayName("문제 묶음 개설 - 완료")
  void createQuestionBundle_success() throws Exception {
    mockMvc.perform(post("/new-question_bundle")
            .param("year", "2020")
            .param("month", "5")
            .param("timeZone", String.valueOf(QuestionBundleTimeZone.TZ0))
            .param("paper", String.valueOf(QuestionBundlePaper.P1))
            .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrlPattern("/question_bundle/*"))
    ;
  }

  @Test
  @WithAccount("robin")
  @DisplayName("문제 묶음 개설 - 실패")
  void createQuestionBundle_fail() throws Exception {
    mockMvc.perform(post("/new-question_bundle")
            .param("year", "2020")
            .param("month", "5")
            .param("timeZone", String.valueOf(QuestionBundleTimeZone.TZ0))
//            .param("paper", String.valueOf(QuestionBundlePaper.P1))
            .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(view().name("question_bundle/form"))
            .andExpect(model().hasErrors())
            .andExpect(model().attributeExists("questionBundleForm"))
            .andExpect(model().attributeExists("account"));
  }

  @Test
  @WithAccount("robin")
  @DisplayName("문제 묶음 개설 폼 조회")
  void viewQuestionBundle() throws Exception {
    QuestionBundle questionBundle = QuestionBundle.createQuestionBundle(2020, 5, QuestionBundleTimeZone.TZ1, QuestionBundlePaper.P1);
    questionBundleService.add(questionBundle);
    mockMvc.perform(get(String.format("/question_bundle/%d", questionBundle.getId())))
            .andExpect(status().isOk())
            .andExpect(view().name("question_bundle/view"))
            .andExpect(model().attributeExists("account"))
            .andExpect(model().attributeExists("questionBundle"));
  }
}