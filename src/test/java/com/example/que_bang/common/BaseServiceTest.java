package com.example.que_bang.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class BaseServiceTest {
  @Autowired
  protected EntityManager em;
}