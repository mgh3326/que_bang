package com.example.que_bang.modules.question_bundle;

import com.example.que_bang.modules.account.Account;
import com.example.que_bang.modules.account.CurrentAccount;
import com.example.que_bang.modules.question.form.QuestionForm;
import com.example.que_bang.modules.question_bundle.form.QuestionBundleForm;
import com.example.que_bang.modules.test_paper.TestPaperService;
import com.example.que_bang.modules.test_paper.TestPaperStatus;
import com.example.que_bang.modules.test_paper.form.TestPaperQuestionBundleForm;
import com.example.que_bang.modules.test_paper.query.TestPaperFlatDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class QuestionBundleController {
  private final QuestionBundleService questionBundleService;
  private final ModelMapper modelMapper;
  private final TestPaperService testPaperService;
  private final static List<Integer> yearList = IntStream.iterate(Calendar.getInstance().get(Calendar.YEAR), i -> i >= 1968, i -> i - 1).boxed().collect(Collectors.toCollection(ArrayList::new));
  private final static List<Integer> monthList = Arrays.asList(5, 11);

  @GetMapping("/question_bundles")
  public String questionBundleIndex(@CurrentAccount Account account, Model model) {
    model.addAttribute(account);
    model.addAttribute("questionBundles", questionBundleService.findAll());
    return "question_bundle/index";
  }

  @GetMapping("/new-question_bundle")
  public String newQuestionBundleForm(@CurrentAccount Account account, Model model) {
    model.addAttribute(account);
    model.addAttribute("yearList", yearList);
    model.addAttribute("monthList", monthList);
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
    QuestionBundle questionBundle = questionBundleService.findOneWithQuestion(id);
    model.addAttribute(account);
    model.addAttribute(questionBundle);
    return "question_bundle/view";
  }

  @GetMapping("/question_bundle/{id}/new-question")
  public String newQuestionForm(@CurrentAccount Account account, @PathVariable Long id, Model model) {
    QuestionBundle questionBundle = questionBundleService.findOne(id);
    model.addAttribute(account);
    model.addAttribute("questionBundle", questionBundle);
    model.addAttribute(new QuestionForm());
    return "question/form";
  }

  @GetMapping("/question_bundle/{id}/new-test_paper")
  public String newTestPaper(@CurrentAccount Account account, @PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
    QuestionBundle questionBundle = questionBundleService.findOne(id);
    model.addAttribute(account);
    redirectAttributes.addAttribute("questionBundleId", questionBundle.getId());
    return "redirect:/new-test_paper";
  }

  @GetMapping("/question_bundle/{id}/add-test_paper")
  public String newTestPaperQuestionBundle(@CurrentAccount Account account, @PathVariable Long id, Model model, @RequestParam(value = "testPaperId") Long testPaperId) {
    model.addAttribute(account);
    testPaperService.addQuestionBundle(testPaperId, id);
    return "redirect:/test_paper/" + testPaperId;
  }

  @GetMapping("/question_bundle/{id}/test_papers")
  public String viewQuestionBundleTestPaperIndex(@CurrentAccount Account account, @PathVariable Long id, Model model) {
    QuestionBundle questionBundle = questionBundleService.findOne(id);
    List<TestPaperFlatDto> testPapers = testPaperService.findAllByStatus(TestPaperStatus.READY, id, null);
    List<TestPaperFlatDto> currentTestPapers = testPaperService.findAllByStatus(null, null, id);

    model.addAttribute("testPapers", testPapers);
    model.addAttribute("currentTestPapers", currentTestPapers);
    model.addAttribute(account);
    model.addAttribute(questionBundle);
    model.addAttribute(new TestPaperQuestionBundleForm());
    return "question_bundle/test_papers";
  }

  @GetMapping("/question_bundle/{id}/edit")
  public String editQuestionBundle(@CurrentAccount Account account, @PathVariable Long id, Model model) {
    QuestionBundle questionBundle = questionBundleService.findOne(id);
    model.addAttribute(account);
    model.addAttribute(questionBundle);
    model.addAttribute(modelMapper.map(questionBundle, QuestionBundleForm.class));
    model.addAttribute("yearList", yearList);
    model.addAttribute("monthList", monthList);
    return "question_bundle/update-form";
  }

  @PostMapping("/question_bundle/{id}/edit")
  public String editQuestionBundleSubmit(@CurrentAccount Account account, @PathVariable Long id, @Valid QuestionBundleForm questionBundleForm, Errors errors, Model model) {
    QuestionBundle questionBundle = questionBundleService.findOne(id);
    if (errors.hasErrors()) {
      model.addAttribute(account);
      model.addAttribute(questionBundle);
      return "question_bundle/update-form";
    }
    questionBundleService.updateQuestionBundleDescription(id, questionBundleForm);
    return "redirect:/question_bundle/" + id.toString();
  }

  @GetMapping("/question_bundle/{id}/delete")
  public String deleteQuestionBundle(@CurrentAccount Account account, @PathVariable Long id, Model model) {
    questionBundleService.deleteOne(id);
    return "redirect:/question_bundles";

  }
}
