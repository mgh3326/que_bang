package com.example.que_bang.modules.main;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Random;

@Getter
@RequiredArgsConstructor
public enum LoveType {
  bbang_ee_ya(200, 0),
  bbu_nee_ya(200, 1),
  love_ya(200, 629),
  o_sen_sae(400, 5),
  dang_gun(400, 6),
  ;
  int statusCode;
  int code;

  LoveType(int statusCode, int code) {
    this.statusCode = statusCode;
    this.code = code;
  }

  private static final List<LoveType> VALUES =
          List.of(values());
  private static final int SIZE = VALUES.size();

  public static LoveType randomLoveType() {
    return VALUES.get(new Random().nextInt(SIZE));
  }
}
