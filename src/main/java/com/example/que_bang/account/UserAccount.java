package com.example.que_bang.account;

import com.example.que_bang.domain.Account;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class UserAccount extends User {

  private Account account;

  public UserAccount(Account account) {
    super(account.getNickname(), account.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
    this.account = account;
  }
}