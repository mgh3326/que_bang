package com.example.que_bang.modules.main;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoveDto {
  String message;
  int statusCode;
  int code;

  public LoveDto(LoveType loveType) {
    this.message = loveType.name();
    this.statusCode = loveType.getStatusCode();
    this.code = loveType.getCode();
  }
}
