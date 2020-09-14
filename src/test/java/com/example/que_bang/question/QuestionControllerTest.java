package com.example.que_bang.question;

import com.example.que_bang.WithAccount;
import com.example.que_bang.common.BaseControllerTest;
import com.example.que_bang.domain.Answer;
import com.example.que_bang.domain.QuestionBundle;
import com.example.que_bang.domain.question.Essay;
import com.example.que_bang.domain.question.QuestionMainTopic;
import com.example.que_bang.domain.question.QuestionSubTopic;
import com.example.que_bang.domain.question.QuestionType;
import com.example.que_bang.domain.question_bundle.QuestionBundlePaper;
import com.example.que_bang.domain.question_bundle.QuestionBundleTimeZone;
import com.example.que_bang.question_bundle.QuestionBundleRepository;
import com.example.que_bang.question_bundle.QuestionBundleService;
import com.example.que_bang.service.QuestionService;
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
class QuestionControllerTest extends BaseControllerTest {
  @Autowired
  protected MockMvc mockMvc;
  @Autowired
  protected QuestionService questionService;
  @Autowired
  protected QuestionBundleService questionBundleService;

  @Test
  @WithAccount("robin")
  @DisplayName("문제 폼 생성")
  void newQuestionSubmit() throws Exception {
    QuestionBundle questionBundle = QuestionBundle.createQuestionBundle(2020, 5, QuestionBundleTimeZone.T1, QuestionBundlePaper.P1);
    questionBundleService.add(questionBundle);
    String content = "문제 내용";
    mockMvc.perform(post("/new-question")
            .param("type", QuestionType.ESSAY.name())
            .param("score", "5")
            .param("content", content)
            .param("answerContent", "정답 내용")
            .param("questionBundleId", questionBundle.getId().toString())
            .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrlPattern("/question/*"))
    ;
    assertEquals(questionBundle.getQuestions().get(0).getContent(), content);
  }

  @Test
  @WithAccount("robin")
  @DisplayName("문제 생성 폼 조회")
  void viewQuestion() throws Exception {
    QuestionBundle questionBundle = QuestionBundle.createQuestionBundle(2020, 5, QuestionBundleTimeZone.T1, QuestionBundlePaper.P1);
    questionBundleService.add(questionBundle);
    String content = "content";
    double score = 0.9;
    QuestionMainTopic mainTopic = QuestionMainTopic.M1;
    QuestionSubTopic subTopic = QuestionSubTopic.S1;
    String answer_content = "answer_content";
    Essay essay = new Essay(content, score, answer_content, mainTopic, subTopic);
    questionService.add(essay);
    questionBundle.addQuestion(essay);
    mockMvc.perform(get("/question/" + essay.getId().toString()))
            .andExpect(status().isOk())
            .andExpect(view().name("question/view"))
            .andExpect(model().attributeExists("account"))
            .andExpect(model().attributeExists("question"));
    ;
  }
}