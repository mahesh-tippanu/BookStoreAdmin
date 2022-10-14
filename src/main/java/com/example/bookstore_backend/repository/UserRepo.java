package com.example.bookstore_backend.repository;

import com.example.bookstore_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    @Query(value = "select * from user_registration_module where user_id= :id", nativeQuery = true)
    Optional<User> getUserById(User id);
    User findByEmailId(String emailId);

}
