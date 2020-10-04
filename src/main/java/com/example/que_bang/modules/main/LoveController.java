package com.example.que_bang.modules.main;

import com.example.que_bang.modules.account.Account;
import com.example.que_bang.modules.account.CurrentAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoveController {
  private final LoveService loveService;

  @GetMapping("/love")
  public String love(@CurrentAccount Account account, Model model) {
    model.addAttribute(account);
    model.addAttribute("love", loveService.randomLoveType());
    return "love";
  }
}
