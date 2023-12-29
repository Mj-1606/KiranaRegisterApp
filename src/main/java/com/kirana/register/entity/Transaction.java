package com.kirana.register.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Time;
import java.util.Date;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy;

@Entity
@Table( name = "Transaction")
public class Transaction {

  @Id
  private String transactionId;
  private String type; // credit or debit
  private Double amount; // transaction amount

  private String currency;

  private String customerName;
  private String productName;
  private String customerAccountNo;

  private String status; // Success or Failed
  private String date; // transaction's date
  private String time; // transaction's time

  public Transaction(){

  }
  public Transaction(String transactionId, String type, Double amount, String customerName,
      String productName, String customerAccountNo, String date, String time) {
    this.transactionId = transactionId;
    this.type = type;
    this.amount = amount;
    this.customerName = customerName;
    this.productName = productName;
    this.customerAccountNo = customerAccountNo;
    this.date = date;
    this.time = time;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }
  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
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

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getCustomerAccountNo() {
    return customerAccountNo;
  }

  public void setCustomerAccountNo(String customerAccountNo) {
    this.customerAccountNo = customerAccountNo;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }
}
