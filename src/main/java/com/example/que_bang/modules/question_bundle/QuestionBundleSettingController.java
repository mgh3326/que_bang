package com.example.que_bang.modules.question_bundle;

import com.example.que_bang.modules.account.Account;
import com.example.que_bang.modules.account.CurrentAccount;
import com.example.que_bang.modules.question_bundle.form.QuestionBundleForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/question_bundle/{id}/settings")
@RequiredArgsConstructor
public class QuestionBundleSettingController {
  private final QuestionBundleService questionBundleService;
  private final ModelMapper modelMapper;
  private final ObjectMapper objectMapper;

  @GetMapping("/description")
  public String viewQuestionBundleSetting(@CurrentAccount Account account, @PathVariable Long id, Model model) {
    QuestionBundle questionBundle = questionBundleService.findOne(id);
    model.addAttribute(account);
    model.addAttribute(questionBundle);
    model.addAttribute(modelMapper.map(questionBundle, QuestionBundleForm.class));
    return "question_bundle/settings/description";
  }

  @PostMapping("/description")
  public String updateQuestionBundleInfo(@CurrentAccount Account account, @PathVariable Long id,
                                         @Valid QuestionBundleForm questionBundleForm, Errors errors,
                                         Model model, RedirectAttributes attributes) {
    QuestionBundle questionBundle = questionBundleService.findOne(id);

    if (errors.hasErrors()) {
      model.addAttribute(account);
      model.addAttribute(questionBundle);
      return "question_bundle/settings/description";
    }
    questionBundleService.updateQuestionBundleDescription(id, questionBundleForm);
    attributes.addFlashAttribute("message", "문제 묶음을 수정했습니다.");
    return "redirect:/question_bundle/" + questionBundle.getId() + "/settings/description";
  }
}
