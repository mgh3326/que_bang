package com.example.que_bang.modules.main;

import org.springframework.stereotype.Service;

@Service
public class LoveService {
  public LoveDto randomLoveType() {
    LoveType loveType = LoveType.randomLoveType();
    System.out.println("loveType = " + loveType);
    return new LoveDto(loveType);
  }
}
