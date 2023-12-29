package com.kirana.register.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Account {

  @Id
  private String accountNo;
  private Double balance;

  private String currency;
  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }
}
