package com.kirana.register.dto;

import java.sql.Time;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class TransactionDto {

  private String customerName;
  private String productName;
  private String customerAccountNo;
  private String type; // credit or debit
  private Double amount; // transaction amount
  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  private String currency;

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getCustomerAccountNo() {
    return customerAccountNo;
  }

  public void setCustomerAccountNo(String customerAccountNo) {
    this.customerAccountNo = customerAccountNo;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }
}
