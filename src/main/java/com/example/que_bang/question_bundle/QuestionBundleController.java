package com.example.que_bang.question_bundle;

import com.example.que_bang.account.CurrentAccount;
import com.example.que_bang.domain.Account;
import com.example.que_bang.domain.QuestionBundle;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.que_bang.question_bundle.form.QuestionBundleForm;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class QuestionBundleController {
  private final QuestionBundleService questionBundleService;
  private final ModelMapper modelMapper;

  @GetMapping("/new-question_bundle")
  public String newQuestionBundleForm(@CurrentAccount Account account, Model model) {
    model.addAttribute(account);
    model.addAttribute(new QuestionBundleForm());
    return "question_bundle/form";
  }

  @PostMapping("/new-question_bundle")
  public String newQuestionBundleSubmit(@CurrentAccount Account account, @Valid QuestionBundleForm questionBundleForm, Errors errors, Model model) {
    if (errors.hasErrors()) {
      model.addAttribute(account);
      return "question_bundle/form";
    }

    Long newQuestionBundleId = questionBundleService.add(modelMapper.map(questionBundleForm, QuestionBundle.class));
    return "redirect:/question_bundle/" + newQuestionBundleId.toString();
  }

  @GetMapping("/question_bundle/{id}")
  public String viewQuestionBundle(@CurrentAccount Account account, @PathVariable Long id, Model model) {
    QuestionBundle questionBundle = questionBundleService.findOne(id);
    model.addAttribute(account);
    model.addAttribute(questionBundle);
    return "question_bundle/view";
  }

  @GetMapping("/question_bundle/{id}/new-question")
  public String newQuestionForm(@CurrentAccount Account account, @PathVariable Long id, Model model) {
    QuestionBundle questionBundle = questionBundleService.findOne(id);
    model.addAttribute(account);
    model.addAttribute(questionBundle);

    return "question/form";
  }
}
