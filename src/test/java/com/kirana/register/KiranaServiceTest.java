package com.kirana.register;


import com.kirana.register.controller.KiranaController;
import com.kirana.register.dao.AccountDao;
import com.kirana.register.dao.TransactionDao;
import com.kirana.register.dto.AccountDto;
import com.kirana.register.entity.Account;
import com.kirana.register.entity.Transaction;
import com.kirana.register.service.KiranaService;
import com.kirana.register.service.impl.KiranaServiceImpl;
import java.sql.Date;
import java.sql.Time;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KiranaServiceTest {

  @InjectMocks
  KiranaServiceImpl kiranaService;
  @Mock
  TransactionDao transactionDao;
  @Mock
  AccountDao accountDao;
  @Test
  public void testGetBalance(){
    Account account=new Account();
    account.setBalance(1000.0);
    account.setAccountNo("12345");
    Mockito.when(accountDao.findById(Mockito.anyString())).thenReturn(Optional.of(account));
    Double actualBalance=kiranaService.getBalance("12345");
    Assertions.assertEquals(1000.0,actualBalance);
  }
  @Test
  public void testUpdateBalance() {
    Account account = new Account();
    account.setBalance(1000.0);
    account.setAccountNo("12345");
    kiranaService.updateBalance(account);
    Mockito.verify(accountDao,Mockito.atMost(1)).save(Mockito.any());
  }
}
