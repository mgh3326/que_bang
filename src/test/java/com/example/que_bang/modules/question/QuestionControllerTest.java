package com.example.que_bang.modules.question;

import com.example.que_bang.modules.account.AccountRepository;
import com.example.que_bang.modules.account.WithAccount;
import com.example.que_bang.modules.common.BaseControllerTest;
import com.example.que_bang.modules.question_bundle.*;
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
class QuestionControllerTest extends BaseControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private QuestionBundleFactory questionBundleFactory;
  @Autowired
  private QuestionFactory questionFactory;
  @Autowired

  private QuestionBundleService questionBundleService;
  @Autowired
  private AccountRepository accountRepository;



  @Test
  @WithAccount("robin")
  @DisplayName("문제 생성 폼 조회")
  void viewQuestion() throws Exception {
    QuestionBundle questionBundle = questionBundleFactory.createQuestionBundle(2020, 5, QuestionBundleTimeZone.T1, QuestionBundlePaper.P1);
    Question question = questionFactory.createQuestionWithAddQuestionBundle(QuestionType.E, "content", 0.9, "answer_content", QuestionMainTopic.M1, QuestionSubTopic.S1, questionBundle.getId());
    mockMvc.perform(get("/question/" + question.getId().toString()))
            .andExpect(status().isOk())
            .andExpect(view().name("question/view"))
            .andExpect(model().attributeExists("account"))
            .andExpect(model().attributeExists("question"));
    ;
  }

  @Test
  @WithAccount("robin")
  @DisplayName("문제 개설 - 완료")
  void createQuestion_success() throws Exception {
    QuestionBundle questionBundle = questionBundleFactory.createQuestionBundle(2020, 5, QuestionBundleTimeZone.T1, QuestionBundlePaper.P1);
    String content = "<p>test</p>";
    mockMvc.perform(post("/new-question")
            .param("type", String.valueOf(QuestionType.E))
            .param("score", "5")
            .param("mainTopic", String.valueOf(QuestionMainTopic.M1))
            .param("subTopic", String.valueOf(QuestionSubTopic.S1))
            .param("content", content)
            .param("answerContent", "<p>test1</p>")
            .param("questionBundleId", String.valueOf(questionBundle.getId()))
            .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrlPattern("/question/*"))
    ;
    questionBundle = questionBundleService.findOneWithQuestion(questionBundle.getId());
    assertEquals(questionBundle.getQuestions().get(0).getContent(), content);
  }

  @Test
  @WithAccount("robin")
  @DisplayName("문제 묶음 개설 - 실패")
  void createQuestion_fail() throws Exception {
    QuestionBundle questionBundle = questionBundleFactory.createQuestionBundle(2020, 5, QuestionBundleTimeZone.T1, QuestionBundlePaper.P1);
    mockMvc.perform(post("/new-question")
            .param("type", String.valueOf(QuestionType.E))
//            .param("score", "5")
            .param("mainTopic", String.valueOf(QuestionMainTopic.M1))
            .param("subTopic", String.valueOf(QuestionSubTopic.S1))
            .param("content", "<p>test</p>")
            .param("answerContent", "<p>test1</p>")
            .param("questionBundleId", String.valueOf(questionBundle.getId()))
            .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(view().name("question/form"))
            .andExpect(model().hasErrors())
            .andExpect(model().attributeExists("questionForm"))
            .andExpect(model().attributeExists("account"));
  }

  @Test
  @WithAccount("robin")
  @DisplayName("문제 수정 폼 조회")
  void editQuestionForm() throws Exception {
    QuestionBundle questionBundle = questionBundleFactory.createQuestionBundle(2020, 5, QuestionBundleTimeZone.T1, QuestionBundlePaper.P1);
    Question question = questionFactory.createQuestionWithAddQuestionBundle(QuestionType.E, "content", 0.9, "answer_content", QuestionMainTopic.M1, QuestionSubTopic.S1, questionBundle.getId());
    mockMvc.perform(get("/question/" + question.getId().toString() + "/edit"))
            .andExpect(status().isOk())
            .andExpect(view().name("question/update-form"))
            .andExpect(model().attributeExists("account"))
            .andExpect(model().attributeExists("question"))
            .andExpect(model().attributeExists("questionBundle"))
            .andExpect(model().attributeExists("questionForm"))
    ;
  }
}