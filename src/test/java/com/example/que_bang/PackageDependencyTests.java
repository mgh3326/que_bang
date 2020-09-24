package com.example.que_bang;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packagesOf = App.class)
public class PackageDependencyTests {
  private static final String ACOUNT = "..modules.account..";
  private static final String QUESTION = "..modules.question..";
  private static final String QUESTION_BUNDLE = "..modules.question_bundle..";
  private static final String TEST_PAPER_QUESTION_BUNDLE = "..modules.test_paper_question_bundle..";
  private static final String TEST_PAPER = "..modules.test_paper..";
  private static final String COMMON = "..modules.common..";

  @ArchTest
  ArchRule modulesPackageRule = classes().that().resideInAPackage("com.example.que_bang.modules..")
          .should().onlyBeAccessed().byClassesThat()
          .resideInAnyPackage("com.example.que_bang.modules..");
  @ArchTest
  ArchRule quebangPackageRule = classes().that().resideInAPackage(QUESTION_BUNDLE)
          .should().onlyBeAccessed().byClassesThat()
          .resideInAnyPackage(QUESTION_BUNDLE, QUESTION, TEST_PAPER_QUESTION_BUNDLE, TEST_PAPER, COMMON);
}
