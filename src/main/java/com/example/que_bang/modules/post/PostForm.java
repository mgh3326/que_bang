package com.example.que_bang.modules.post;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class PostForm {
  @NotNull
  String type;
  @NotNull
  String content;
  @NotNull
  String title;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  LocalDate date = LocalDate.now();
}
