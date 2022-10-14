package com.example.bookstore_backend.controller;

import com.example.bookstore_backend.dto.OrderDTO;
import com.example.bookstore_backend.dto.ResponseDTO;
import com.example.bookstore_backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;


    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> placeOrder(@RequestBody OrderDTO orderDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Order Place Successful", orderService.placeOrder(orderDTO));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.CREATED);
    }
    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getAll(){
        ResponseDTO responseDTO = new ResponseDTO("Here are all the Cart Items.." , orderService.getUserID());
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }
}