package com.kirana.register.service;

import com.kirana.register.dto.AccountDto;
import com.kirana.register.dto.TransactionDto;
import com.kirana.register.entity.Account;

public interface KiranaService {
  Double getBalance(String accountNo) ;
   void updateBalance(Account account);

  public String performTransaction(TransactionDto transactionDto) throws Exception;

  void addAccount(AccountDto accountDto);
}
