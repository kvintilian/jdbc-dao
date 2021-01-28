package com.example.jdbcdao.entity;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "orders")
@Check(constraints = "amount > 0")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private Date date;

  private String productName;

  @ManyToOne()
  private Customer customer;

  private int amount;

  public void setId(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }
}
