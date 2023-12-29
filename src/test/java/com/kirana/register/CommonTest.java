package com.kirana.register;


import com.kirana.register.service.impl.KiranaServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommonTest {

  @Autowired
  KiranaServiceImpl kiranaService;

  @Test
  void test(){
    Double inrValue=kiranaService.convertUSDToINR(20.0);
    System.out.println("converted value :  "+inrValue);
//    Assertions.assertEquals(inrValue,1662.5815278339999); //as it is being updated realtime we cannot test this
  }
}
