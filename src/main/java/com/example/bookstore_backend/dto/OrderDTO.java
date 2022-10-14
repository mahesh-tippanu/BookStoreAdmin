package com.example.bookstore_backend.dto;

import com.example.bookstore_backend.model.Cart;
import com.example.bookstore_backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
       public long cartId;
       int quantity;
       double totalPrice;
       private String address;
}