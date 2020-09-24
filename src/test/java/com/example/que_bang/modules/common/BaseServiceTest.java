package com.example.que_bang.modules.common;

import com.example.que_bang.modules.account.AccountRepository;
import com.example.que_bang.modules.question.Question;
import com.example.que_bang.modules.question.QuestionRepository;
import com.example.que_bang.modules.question_bundle.QuestionBundleRepository;
import com.example.que_bang.modules.test_paper.TestPaperQuestionBundleRepository;
import com.example.que_bang.modules.test_paper.TestPaperRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;

@ActiveProfiles("test")
@SpringBootTest
public class BaseServiceTest {
  @Autowired
  protected EntityManager em;
  @Autowired
  protected JdbcTemplate jdbcTemplate;
  @Autowired
  private AccountRepository accountRepository;
  @Autowired
  private TestPaperRepository testPaperRepository;
  @Autowired
  private QuestionBundleRepository questionBundleRepository;
  @Autowired
  private QuestionRepository<Question> questionRepository;
  @Autowired
  private TestPaperQuestionBundleRepository testPaperQuestionBundleRepository;

  @AfterEach
  void deleteAll() {
    accountRepository.deleteAllByIdInQuery();
    questionRepository.deleteAllByIdInQuery();
    testPaperQuestionBundleRepository.deleteAllByIdInQuery();
    questionBundleRepository.deleteAllByIdInQuery();
    testPaperRepository.deleteAllByIdInQuery();
  }
}
