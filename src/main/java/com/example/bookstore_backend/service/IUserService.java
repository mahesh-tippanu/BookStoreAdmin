package com.example.bookstore_backend.service;

import com.example.bookstore_backend.dto.LoginDTO;
import com.example.bookstore_backend.dto.UserDTO;
import com.example.bookstore_backend.model.User;

import java.util.List;

public interface IUserService {
    String loginUser(LoginDTO loginDTO);

    List<User> getUserInfo();

    com.example.bookstore_backend.model.User registerUser(UserDTO userDTO);
}
