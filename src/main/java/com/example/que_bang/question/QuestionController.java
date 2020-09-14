package com.example.que_bang.question;

import com.example.que_bang.account.CurrentAccount;
import com.example.que_bang.domain.Account;
import com.example.que_bang.domain.Question;
import com.example.que_bang.domain.QuestionBundle;
import com.example.que_bang.question.form.QuestionForm;
import com.example.que_bang.question_bundle.form.QuestionBundleForm;
import com.example.que_bang.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class QuestionController {
  private final QuestionService questionService;

  @PostMapping("/new-question")
  public String newQuestionSubmit(@CurrentAccount Account account, @Valid QuestionForm questionForm, Errors errors, Model model) {
    if (errors.hasErrors()) {
      model.addAttribute(account);
      return "question/form";
    }
    Long newQuestionId = questionService.addForm(questionForm);
    return "redirect:/question/" + newQuestionId.toString();
  }

  @GetMapping("/question/{id}")
  public String viewQuestion(@CurrentAccount Account account, @PathVariable Long id, Model model) {
    Question question = questionService.findOneWithQuestionBundle(id);
    model.addAttribute(account);
    model.addAttribute(question.getQuestionBundle());
    model.addAttribute("question", question);
    return "question/view";
  }
}
