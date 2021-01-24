package com.example.jdbcdao.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

@Repository
public class ProductRepository {

  private final String query;

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  public ProductRepository() {
    this.query = read("select_order_by_customer.sql");
  }

  public String getProductName(String name) {
    SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(query, Map.of("CUSTOMERNAME", name));
    StringBuilder stringBuilder = new StringBuilder();
    while (sqlRowSet.next()) {
      if (stringBuilder.length() > 0) stringBuilder.append(", ");
      stringBuilder.append(sqlRowSet.getString("product_name"));
    }
    return stringBuilder.toString();
  }

  public String read(String fileName) {
    StringBuilder result = new StringBuilder();
    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        result.append(line).append("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result.toString();
  }

}
