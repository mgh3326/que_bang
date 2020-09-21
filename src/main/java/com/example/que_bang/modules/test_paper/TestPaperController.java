package com.example.que_bang.modules.test_paper;

import com.example.que_bang.modules.account.Account;
import com.example.que_bang.modules.account.CurrentAccount;
import com.example.que_bang.modules.question.Question;
import com.example.que_bang.modules.question.form.QuestionForm;
import com.example.que_bang.modules.test_paper.form.TestPaperForm;
import com.example.que_bang.modules.test_paper.query.TestPaperQueryDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import static com.example.que_bang.modules.question.QQuestion.question;


@Controller
@RequiredArgsConstructor
public class TestPaperController {
  private final ModelMapper modelMapper;
  private final TestPaperService testPaperService;

  @GetMapping("/new-test_paper")
  public String newTestPaperForm(@CurrentAccount Account account, Model model, @RequestParam(value = "questionBundleId", required = false) Long questionBundleId) {
    model.addAttribute(account);
    TestPaperForm testPaperForm = new TestPaperForm();
    if (questionBundleId != null) {
      testPaperForm.setQuestionBundleId(questionBundleId);
    }
    model.addAttribute(testPaperForm);
    return "test_paper/form";
  }

  @PostMapping("/new-test_paper")
  public String newQuestionBundleSubmit(@CurrentAccount Account account, @Valid TestPaperForm testPaperForm, Errors errors, Model model) {
    if (errors.hasErrors()) {
      model.addAttribute(account);
      return "test_paper/form";
    }

    Long newTestPaperId = testPaperService.add(modelMapper.map(testPaperForm, TestPaper.class));
    if (testPaperForm.getQuestionBundleId() != null) {
      testPaperService.addQuestionBundle(newTestPaperId, testPaperForm.getQuestionBundleId());
    }
    return "redirect:/test_paper/" + newTestPaperId.toString();
  }

  @GetMapping("/test_paper/{id}")
  public String viewQuestionBundle(@CurrentAccount Account account, @PathVariable Long id, Model model) {
    TestPaperQueryDto testPaper = testPaperService.findOneWithQuestionBundle(id);
    model.addAttribute(account);
    model.addAttribute("testPaper", testPaper);
    return "test_paper/view";
  }

  @GetMapping("/test_papers")
  public String indexTestPaper(@CurrentAccount Account account, Model model) {
    model.addAttribute(account);
    model.addAttribute("testPapers", testPaperService.findAllByStatus(null, null, null));
    return "test_paper/index";
  }

  @GetMapping("/test_paper/{id}/edit")
  public String editTestPaperForm(@CurrentAccount Account account, @PathVariable Long id, Model model) {
    TestPaperQueryDto testPaper = testPaperService.findOneWithQuestionBundle(id);
    model.addAttribute("testPaper", testPaper);
    model.addAttribute(modelMapper.map(testPaper, TestPaperForm.class));
    model.addAttribute(account);
    return "test_paper/update-form";
  }

  @PostMapping("/test_paper/{id}/edit")
  public String editTestPaper(@CurrentAccount Account account, @PathVariable Long id, @Valid TestPaperForm testPaperForm, Errors errors, Model model) {
    TestPaperQueryDto testPaper = testPaperService.findOneWithQuestionBundle(id);

    if (errors.hasErrors()) {
      model.addAttribute(account);
      model.addAttribute(testPaper);
      return "question_bundle/update-form";
    }
    testPaperService.updateFromForm(id, testPaperForm);

    return "redirect:/test_paper/" + id.toString();
  }
}
