package com.example.que_bang.modules.account;

import com.example.que_bang.modules.common.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AccountControllerTest extends BaseControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private AccountRepository accountRepository;



  @DisplayName("회원 가입 화면 보이는지 테스트")
  @Test
  void signUpForm() throws Exception {
    mockMvc.perform(get("/sign-up"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("account/sign-up"))
            .andExpect(model().attributeExists("signUpForm"))
            .andExpect(unauthenticated());
  }

  @DisplayName("회원 가입 처리 - 입력값 오류")
  @Test
  void signUpSubmit_with_wrong_input() throws Exception {
    mockMvc.perform(post("/sign-up")
            .param("nickname", "robin")
            .param("email", "email..")
            .param("password", "12345")
            .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(view().name("account/sign-up"))
            .andExpect(unauthenticated());
  }

  @DisplayName("회원 가입 처리 - 입력값 정상")
  @Test
  void signUpSubmit_with_correct_input() throws Exception {
    mockMvc.perform(post("/sign-up")
            .param("nickname", "robin")
            .param("email", "robin@email.com")
            .param("password", "12345678")
            .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/"))
            .andExpect(authenticated().withUsername("robin"));

    Account account = accountRepository.findByEmail("robin@email.com");
    assertNotNull(account);
    assertNotEquals(account.getPassword(), "12345678");
    assertNotNull(account.getEmailCheckToken());
  }
}