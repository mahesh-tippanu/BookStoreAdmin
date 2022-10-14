package com.example.bookstore_backend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@Slf4j
public class BookStoreBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookStoreBackendApplication.class, args);
        System.out.println("Welcome to Book Store");
    }
}