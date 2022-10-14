package com.example.bookstore_backend.repository;

import com.example.bookstore_backend.model.Address;
import com.example.bookstore_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {

}