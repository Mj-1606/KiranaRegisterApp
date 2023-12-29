package com.kirana.register.controller;

import com.kirana.register.dto.AccountDto;
import com.kirana.register.dto.TransactionDto;
import com.kirana.register.exception.InsufficientBalanceException;
import com.kirana.register.service.KiranaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kirana")
public class KiranaController {

  @Autowired
  KiranaService kiranaService;

  @PostMapping("/transaction")
  ResponseEntity<String> performTransactionRequest(@RequestBody TransactionDto transactionDto){
    String message;
      try{
        message = kiranaService.performTransaction(transactionDto);
        return new ResponseEntity<>(message, HttpStatus.OK);
      }

    catch (InsufficientBalanceException ex) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    catch(Exception e){
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @PostMapping("/addAccount")
  ResponseEntity<String> addAccount(@RequestBody AccountDto accountDto){
      kiranaService.addAccount(accountDto);
    return new ResponseEntity<>("Account Added", HttpStatus.OK);
  }
  @GetMapping("/accountInfo/{accountNo}")
  ResponseEntity<String> getAccountInfo(@PathVariable String accountNo){
    try {
     Double balance= kiranaService.getBalance(accountNo);
      return new ResponseEntity<>("Balance is "+balance, HttpStatus.OK);
    }
    catch(Exception e){
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
