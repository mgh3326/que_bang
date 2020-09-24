package com.example.que_bang.modules.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account, Long> {

  boolean existsByEmail(String email);

  boolean existsByNickname(String nickname);

  Account findByEmail(String email);

  Account findByNickname(String nickname);

  @Transactional
  @Modifying
  @Query("delete from Account a where a.id in (select a2.id from Account a2)")
  void deleteAllByIdInQuery();
}