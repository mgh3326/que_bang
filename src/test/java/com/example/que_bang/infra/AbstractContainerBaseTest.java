package com.example.que_bang.infra;

import org.testcontainers.containers.MySQLContainer;

public abstract class AbstractContainerBaseTest {

  static final MySQLContainer MYSQL_CONTAINER;

  static {
    MYSQL_CONTAINER = new MySQLContainer();
    MYSQL_CONTAINER.start();
  }
}
