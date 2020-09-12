package com.example.que_bang.question;

import com.example.que_bang.account.CurrentAccount;
import com.example.que_bang.domain.Account;
import com.example.que_bang.domain.QuestionBundle;
import com.example.que_bang.question_bundle.QuestionBundleService;
import com.example.que_bang.question_bundle.form.QuestionBundleForm;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

//@Controller
//@RequiredArgsConstructor
//public class QuestionController {
//  private final QuestionController questionController;
//  private final ModelMapper modelMapper;
//}
