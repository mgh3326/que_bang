package com.example.que_bang.modules.account;

import com.example.que_bang.modules.common.BaseTimeEntity;
import com.example.que_bang.modules.zone.Zone;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseTimeEntity {

  @Id
  @GeneratedValue
  private Long id;

  @Column(unique = true)
  private String email;

  @Column(unique = true)
  private String nickname;

  private String password;

  private boolean emailVerified;

  private String emailCheckToken;

  private LocalDateTime emailCheckTokenGeneratedAt;

  private LocalDateTime joinedAt;

  private String bio;

  private String url;

  private String occupation;

  private String location;

  @Lob
  @Basic(fetch = FetchType.EAGER)
  private String profileImage;

  private boolean studyCreatedByEmail;

  private boolean studyCreatedByWeb = true;

  private boolean studyEnrollmentResultByEmail;

  private boolean studyEnrollmentResultByWeb = true;

  private boolean studyUpdatedByEmail;

  private boolean studyUpdatedByWeb = true;

  @ManyToMany
  private Set<Zone> zones = new HashSet<>();

  public void generateEmailCheckToken() {
    this.emailCheckToken = UUID.randomUUID().toString();
    this.emailCheckTokenGeneratedAt = LocalDateTime.now();
  }

  public void completeSignUp() {
    this.emailVerified = true;
    this.joinedAt = LocalDateTime.now();
  }

  public boolean isValidToken(String token) {
    return this.emailCheckToken.equals(token);
  }

  public boolean canSendConfirmEmail() {
    return this.emailCheckTokenGeneratedAt.isBefore(LocalDateTime.now().minusHours(1));
  }

  public Account(String email, String nickname, String password, boolean studyCreatedByWeb, boolean studyEnrollmentResultByWeb, boolean studyUpdatedByWeb) {
    this.email = email;
    this.nickname = nickname;
    this.password = password;
    this.studyCreatedByWeb = studyCreatedByWeb;
    this.studyEnrollmentResultByWeb = studyEnrollmentResultByWeb;
    this.studyUpdatedByWeb = studyUpdatedByWeb;
  }

  public static Account CreateAccount(String email, String nickname, String password, boolean studyCreatedByWeb, boolean studyEnrollmentResultByWeb, boolean studyUpdatedByWeb) {
    Account account = new Account();
    account.email = email;
    account.nickname = nickname;
    account.password = password;
    account.studyCreatedByWeb = studyCreatedByWeb;
    account.studyEnrollmentResultByWeb = studyEnrollmentResultByWeb;
    account.studyUpdatedByWeb = studyUpdatedByWeb;
    return account;
  }
}
