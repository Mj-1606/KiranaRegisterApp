package com.kirana.register;


import com.kirana.register.service.impl.KiranaServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class test {

  @Autowired
  KiranaServiceImpl kiranaService;

  @Test
  void test(){
    Double inrValue=kiranaService.convertUSDToINR(20.0);
    System.out.println("converted value :  "+inrValue);
  }
}
