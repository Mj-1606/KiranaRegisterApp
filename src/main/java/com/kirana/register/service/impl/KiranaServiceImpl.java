package com.kirana.register.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirana.register.dao.AccountDao;
import com.kirana.register.dao.TransactionDao;
import com.kirana.register.dto.AccountDto;
import com.kirana.register.dto.TransactionDto;
import com.kirana.register.entity.Account;
import com.kirana.register.entity.Transaction;
import com.kirana.register.exception.InsufficientBalanceException;
import com.kirana.register.service.KiranaService;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Scope("singleton")
public class KiranaServiceImpl implements KiranaService {

  private static final Logger log = LogManager.getLogger(KiranaServiceImpl.class);
  @Autowired
  AccountDao accountDao;

  @Autowired
  TransactionDao transactionDao;

  RestTemplate restTemplate=new RestTemplate();

  private final Lock transactionLock = new ReentrantLock(); // to ensure only one thread can access data at a time.

  @Transactional
  public Double getBalance(String accountNo) {
    transactionLock.lock();
     Account account= accountDao.findById(accountNo).get();
    transactionLock.unlock();
      return account.getBalance();

  }
  @Transactional
  public void updateBalance(Account account){
    transactionLock.lock();
    try{
      accountDao.save(account);
      }
    catch (RuntimeException e){
      transactionLock.unlock(); // to prevent deadlock
    }
    finally{
      transactionLock.unlock();
    }
  }

  @Transactional
  public String performTransaction(TransactionDto transactionDto) throws InsufficientBalanceException,Exception{
    transactionLock.lock();
    LocalTime currentTime = LocalTime.now();
    String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    LocalDate currentDate = LocalDate.now();
    String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    Transaction transaction = new Transaction(UUID.randomUUID().toString(),
        transactionDto.getType(),
        transactionDto.getAmount(),
        transactionDto.getCustomerName(),
        transactionDto.getProductName(),
        transactionDto.getCustomerAccountNo(),
        formattedDate,
        formattedTime);
    try {
      Account account = accountDao.findById("12345").get();
        if (transactionDto.getType().equalsIgnoreCase("Debit")
            && account.getBalance() - transactionDto.getAmount() < 0) {
          throw new InsufficientBalanceException("Insufficient Balance , transaction Failed");
        }

        if(transactionDto.getCurrency().equalsIgnoreCase("usd")){
          Double convertedAmount = convertUSDToINR(transactionDto.getAmount());
          transactionDto.setAmount(convertedAmount);
        }
        transaction.setStatus("Success");
        transactionDao.save(transaction);
        if(transactionDto.getType().equalsIgnoreCase("debit"))
        {
          account.setBalance(account.getBalance()-transactionDto.getAmount());
          updateBalance(account);
        }else{
          account.setBalance(account.getBalance()+transactionDto.getAmount());
          updateBalance(account);
        }

    }
    catch (InsufficientBalanceException ex){
      transaction.setStatus("Failed");
      transactionDao.save(transaction);
      transactionLock.unlock();
      throw new InsufficientBalanceException(ex.getMessage());
    }
    catch (Exception ex){
      transaction.setStatus("Failed");
      transactionDao.save(transaction);
      transactionLock.unlock();
      throw new Exception(ex.getMessage());
    }
    finally{
      transactionLock.unlock();
    }
    return "transaction is "+transaction.getStatus();
  }

  @Override
  public void addAccount(AccountDto accountDto) {
    transactionLock.lock();
    Account account=new Account();
    account.setAccountNo(accountDto.getAccountNo());
    account.setBalance(accountDto.getBalance());
    accountDao.save(account);
    transactionLock.unlock();
  }

  public Double convertUSDToINR(Double amount){
    double conversionFactor=1.0 ;
    ResponseEntity<Object> response =restTemplate.getForEntity("https://api.fxratesapi.com/latest",Object.class);
    if(response.getStatusCode().value()==200) {
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode jsonNode = objectMapper.valueToTree(response.getBody());
      conversionFactor = jsonNode.get("rates").get("INR").asDouble();
      log.info("conversionFactor : {}",conversionFactor);

    }
    return amount*conversionFactor;
  }
}
