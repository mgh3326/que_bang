package com.example.que_bang.account;


import com.example.que_bang.account.form.SignUpForm;
import com.example.que_bang.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AccountController {

  private final SignUpFormValidator signUpFormValidator;
  private final AccountService accountService;

  @InitBinder("signUpForm")
  public void initBinder(WebDataBinder webDataBinder) {
    webDataBinder.addValidators(signUpFormValidator);
  }

  @GetMapping("/sign-up")
  public String signUpForm(Model model) {
    model.addAttribute(new SignUpForm());
    return "account/sign-up";
  }

  @PostMapping("/sign-up")
  public String signUpSubmit(@Valid SignUpForm signUpForm, Errors errors) {
    if (errors.hasErrors()) {
      return "account/sign-up";
    }
    Account account = accountService.processNewAccount(signUpForm);
    accountService.login(account);
    return "redirect:/";
  }
}
