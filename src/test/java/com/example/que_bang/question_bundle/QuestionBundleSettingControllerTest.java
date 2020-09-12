package com.example.que_bang.question_bundle;

import com.example.que_bang.WithAccount;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RequiredArgsConstructor
class QuestionBundleSettingControllerTest extends BaseControllerTest {
  @Autowired
  protected MockMvc mockMvc;
  @Autowired
  protected QuestionBundleService questionBundleService;
  @Autowired
  protected QuestionBundleRepository questionBundleRepository;

  @Test
  @WithAccount("robin")
  @DisplayName("문제 묶음 소개 수정 폼 조회")
  void viewQuestionBundleSetting() throws Exception {
    QuestionBundle questionBundle = QuestionBundle.createQuestionBundle(2020, 5, QuestionBundleTimeZone.TZ1, QuestionBundlePaper.P1);
    questionBundleService.add(questionBundle);

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
    QuestionBundle questionBundle = QuestionBundle.createQuestionBundle(2020, 5, QuestionBundleTimeZone.TZ1, QuestionBundlePaper.P1);
    questionBundleService.add(questionBundle);

    String settingsDescriptionUrl = "/question_bundle/" + questionBundle.getId() + "/settings/description";
    mockMvc.perform(post(settingsDescriptionUrl)
            .param("year", "2007")
            .param("month", "5")
            .param("timeZone", QuestionBundleTimeZone.TZ1.toString())
            .param("paper", QuestionBundlePaper.P1.toString())
            .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl(settingsDescriptionUrl))
            .andExpect(flash().attributeExists("message"));
    assertEquals(questionBundleService.findOne(questionBundle.getId()).getYear(), 2007);
  }
}