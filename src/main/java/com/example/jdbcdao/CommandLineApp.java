package com.example.jdbcdao;

import com.example.jdbcdao.entity.Customer;
import com.example.jdbcdao.entity.Order;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Component
public class CommandLineApp implements CommandLineRunner {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    var names = List.of("Петя", "Иван", "Саша", "Петр", "Инокентий", "Алексей");
    var surnames = List.of("Иванов", "Петров", "Сидоров", "Сковородкин", "Рыжий");
    var productNames = List.of("Шар", "Носок", "Ручка", "Карандаш", "Ничего", "Что-то еще");

    var random = new Random();
    names.forEach(s -> {
      var customer = new Customer();
      customer.setAge(random.nextInt(30) + 1);
      customer.setName(s);
      customer.setSurname(surnames.get(random.nextInt(surnames.size())));
      customer.setPhoneNumber(Integer.toString(random.nextInt(500)));

      List<Order> orderList = new ArrayList<>();
      IntStream.range(0, random.nextInt(4)).forEach(i -> {
        Order o = new Order();
        o.setDate(new Date());
        o.setAmount(random.nextInt(500) + 1);
        o.setCustomer(customer);
        o.setProductName(productNames.get(random.nextInt(productNames.size())));
        orderList.add(o);
        entityManager.persist(o);
      });

      customer.setOrders(orderList);
      entityManager.persist(customer);
    });
  }
}
