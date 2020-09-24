package com.example.que_bang.modules.main;

import com.example.que_bang.modules.account.AccountRepository;
import com.example.que_bang.modules.account.AccountService;
import com.example.que_bang.modules.account.form.SignUpForm;
import com.example.que_bang.modules.common.BaseControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MainControllerTest extends BaseControllerTest {

  @Autowired
  MockMvc mockMvc;
  @Autowired
  AccountService accountService;
  @Autowired
  AccountRepository accountRepository;

  @BeforeEach
  void beforeEach() {
    SignUpForm signUpForm = new SignUpForm();
    signUpForm.setNickname("robin");
    signUpForm.setEmail("robin@email.com");
    signUpForm.setPassword("12345678");
    accountService.processNewAccount(signUpForm);
  }



  @DisplayName("이메일로 로그인 성공")
  @Test
  void login_with_email() throws Exception {
    mockMvc.perform(post("/login")
            .param("username", "robin@email.com")
            .param("password", "12345678")
            .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"))
            .andExpect(authenticated().withUsername("robin"));
  }

  @DisplayName("이메일로 로그인 성공")
  @Test
  void login_with_nickname() throws Exception {
    mockMvc.perform(post("/login")
            .param("username", "robin")
            .param("password", "12345678")
            .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"))
            .andExpect(authenticated().withUsername("robin"));
  }

  @DisplayName("로그인 실패")
  @Test
  void login_fail() throws Exception {
    mockMvc.perform(post("/login")
            .param("username", "111111")
            .param("password", "000000000")
            .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/login?error"))
            .andExpect(unauthenticated());
  }

  @WithMockUser
  @DisplayName("로그아웃")
  @Test
  void logout() throws Exception {
    mockMvc.perform(post("/logout")
            .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"))
            .andExpect(unauthenticated());
  }

}