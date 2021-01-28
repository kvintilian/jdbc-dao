package com.example.jdbcdao.entity;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.List;

@Entity(name = "customers")
@Check(constraints = "age > 0")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;
  private String surname;
  private int age;
  private String phoneNumber;

  @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
  private List<Order> orders;

  public void setId(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }
}
