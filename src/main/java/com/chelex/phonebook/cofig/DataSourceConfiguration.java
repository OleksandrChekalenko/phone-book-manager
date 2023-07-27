//package com.chelex.phonebook.cofig;
//
//import javax.sql.DataSource;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//@Configuration
//@RequiredArgsConstructor
//public class DataSourceConfiguration {
//
//  private final Environment env;
//
//  TO manually configure data source configuration
//
//    @Bean
//  public DataSource dataSource() {
//    DriverManagerDataSource dataSource = new DriverManagerDataSource();
////    dataSource.setDriverClassName(env.getProperty("app.datasource.driverClassName"));
//    dataSource.setUrl(env.getProperty("spring.datasource.url"));
//    dataSource.setUsername(env.getProperty("spring.datasource.username"));
//    dataSource.setPassword(env.getProperty("spring.datasource.password"));
//    return dataSource;
//  }
//}
