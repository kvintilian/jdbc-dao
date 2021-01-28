package com.example.jdbcdao.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Locale;

@Repository
public class ProductRepository {

  @PersistenceContext
  EntityManager entityManager;

  public List<String> getProductName(String name) {
    return entityManager.createQuery(
            "SELECT productName FROM orders WHERE UPPER(customer.name) = :cname", String.class)
            .setParameter("cname", name.toUpperCase(Locale.ROOT))
            .getResultList();
  }
}
