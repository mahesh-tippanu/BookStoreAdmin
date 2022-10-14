package com.example.bookstore_backend.service;

import com.example.bookstore_backend.dto.CartDTO;
import com.example.bookstore_backend.dto.OrderDTO;
import com.example.bookstore_backend.exception.BookException;
import com.example.bookstore_backend.model.Book;
import com.example.bookstore_backend.model.Cart;
import com.example.bookstore_backend.model.Order;
import com.example.bookstore_backend.model.User;
import com.example.bookstore_backend.repository.CartRepo;
import com.example.bookstore_backend.repository.OrderRepo;
import com.example.bookstore_backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class OrderService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserService userService;
    @Autowired
    CartRepo cartRepo;
    @Autowired
    CartService cartService;
    @Autowired
    OrderRepo orderRepo;
    public Order placeOrder(OrderDTO orderDTO) {
        //Optional<User> user = userRepo.findById(orderDTO.user_Id);
        Optional<Cart> cart = cartRepo.findById(orderDTO.cartId);
        if (cart.isPresent()) {
            Order order = new Order(cart.get(), orderDTO.getQuantity(), orderDTO.getTotalPrice(), orderDTO.getAddress());
            orderRepo.save(order);
            //cartService.emptyCart();
            return order;
        } else{
            throw new BookException("Cart Id not present");
        }
    }
    public List<Order> getUserID() {
        return orderRepo.findAll();
    }
}
