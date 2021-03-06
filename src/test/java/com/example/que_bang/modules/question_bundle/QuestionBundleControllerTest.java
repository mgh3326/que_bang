package com.example.que_bang.modules.question_bundle;

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
class QuestionBundleControllerTest extends BaseControllerTest {
  @Autowired
  protected MockMvc mockMvc;
  @Autowired
  protected QuestionBundleService questionBundleService;
  @Autowired
  QuestionBundleFactory questionBundleFactory;
  @Autowired
  private AccountRepository accountRepository;


  @Test
  @WithAccount("robin")
  @DisplayName("문제 묶음 index 조회")
  void index() throws Exception {
    mockMvc.perform(get("/question_bundles"))
            .andExpect(status().isOk())
            .andExpect(view().name("question_bundle/index"))
            .andExpect(model().attributeExists("account"))
            .andExpect(model().attributeExists("questionBundles"));
  }

  @Test
  @WithAccount("robin")
  @DisplayName("문제 묶음 개설 폼 조회")
  void createQuestionBundleForm() throws Exception {
    mockMvc.perform(get("/new-question_bundle"))
            .andExpect(status().isOk())
            .andExpect(view().name("question_bundle/form"))
            .andExpect(model().attributeExists("account"))
            .andExpect(model().attributeExists("questionBundleForm"))
            .andExpect(model().attributeExists("yearList"))
            .andExpect(model().attributeExists("monthList"));
  }

  @Test
  @WithAccount("robin")
  @DisplayName("문제 묶음 개설 - 완료")
  void createQuestionBundle_success() throws Exception {
    mockMvc.perform(post("/new-question_bundle")
            .param("year", "2020")
            .param("month", "5")
            .param("timeZone", String.valueOf(QuestionBundleTimeZone.T0))
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
            .param("timeZone", String.valueOf(QuestionBundleTimeZone.T0))
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
    QuestionBundle questionBundle = questionBundleFactory.createQuestionBundle(2020, 5, QuestionBundleTimeZone.T1, QuestionBundlePaper.P1);
    mockMvc.perform(get(String.format("/question_bundle/%d", questionBundle.getId())))
            .andExpect(status().isOk())
            .andExpect(view().name("question_bundle/view"))
            .andExpect(model().attributeExists("account"))
            .andExpect(model().attributeExists("questionBundle"));
  }

  @Test
  @WithAccount("robin")
  @DisplayName("문제 개설 폼 조회")
  void createQuestionForm() throws Exception {
    QuestionBundle questionBundle = questionBundleFactory.createQuestionBundle(2020, 5, QuestionBundleTimeZone.T1, QuestionBundlePaper.P1);
    mockMvc.perform(get(String.format("/question_bundle/%d/new-question", questionBundle.getId())))
            .andExpect(status().isOk())
            .andExpect(view().name("question/form"))
            .andExpect(model().attributeExists("account"))
            .andExpect(model().attributeExists("questionForm"))
            .andExpect(model().attributeExists("questionBundle"));
  }

  @Test
  @WithAccount("robin")
  @DisplayName("문제 묶음 수정 폼")
  void editQuestionBundle_form() throws Exception {
    QuestionBundle questionBundle = questionBundleFactory.createQuestionBundle(2020, 5, QuestionBundleTimeZone.T1, QuestionBundlePaper.P1);
    mockMvc.perform(get(String.format("/question_bundle/%d/edit", questionBundle.getId())))
            .andExpect(status().isOk())
            .andExpect(view().name("question_bundle/update-form"))
            .andExpect(model().attributeExists("account"))
            .andExpect(model().attributeExists("questionBundleForm"))
            .andExpect(model().attributeExists("yearList"))
            .andExpect(model().attributeExists("monthList"));
    ;
  }
}