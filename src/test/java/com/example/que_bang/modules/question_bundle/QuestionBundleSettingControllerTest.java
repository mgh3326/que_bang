package com.example.que_bang.modules.question_bundle;

import com.example.que_bang.modules.account.WithAccount;
import com.example.que_bang.modules.common.BaseControllerTest;
import com.example.que_bang.modules.question.QuestionFactory;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RequiredArgsConstructor
class QuestionBundleSettingControllerTest extends BaseControllerTest {
  @Autowired
  protected MockMvc mockMvc;
  @Autowired
  protected QuestionBundleService questionBundleService;
  @Autowired
  protected QuestionBundleRepository questionBundleRepository;
  @Autowired
  QuestionBundleFactory questionBundleFactory;

  @Test
  @WithAccount("robin")
  @DisplayName("문제 묶음 소개 수정 폼 조회")
  void viewQuestionBundleSetting() throws Exception {
    QuestionBundle questionBundle = questionBundleFactory.createQuestionBundle(2020, 5, QuestionBundleTimeZone.T1, QuestionBundlePaper.P1);

    mockMvc.perform(get("/question_bundle/" + questionBundle.getId() + "/settings/description"))
            .andExpect(status().isOk())
            .andExpect(view().name("question_bundle/settings/description"))
            .andExpect(model().attributeExists("questionBundleForm"))
            .andExpect(model().attributeExists("account"))
            .andExpect(model().attributeExists("questionBundle"));

  }

  @Test
  @WithAccount("robin")
  @DisplayName("문제 묶음 소개 수정 폼 조회")
  void updateQuestionBundleInfo() throws Exception {
    QuestionBundle questionBundle = questionBundleFactory.createQuestionBundle(2020, 5, QuestionBundleTimeZone.T1, QuestionBundlePaper.P1);

    String settingsDescriptionUrl = "/question_bundle/" + questionBundle.getId() + "/settings/description";
    mockMvc.perform(post(settingsDescriptionUrl)
            .param("year", "2007")
            .param("month", "5")
            .param("timeZone", QuestionBundleTimeZone.T1.toString())
            .param("paper", QuestionBundlePaper.P1.toString())
            .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl(settingsDescriptionUrl))
            .andExpect(flash().attributeExists("message"));
    assertEquals(questionBundleService.findOne(questionBundle.getId()).getYear(), 2007);
  }
}