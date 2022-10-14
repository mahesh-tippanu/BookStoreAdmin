package com.example.bookstore_backend.controller;

import com.example.bookstore_backend.dto.AddressDTO;
import com.example.bookstore_backend.dto.ResponseDTO;
import com.example.bookstore_backend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/address")
@RestController
public class AddressController {
       @Autowired
       AddressService service;

        @PostMapping("/add")
        public ResponseEntity<ResponseDTO> addAddress(@RequestBody AddressDTO dto) {
            ResponseDTO responseDto = new ResponseDTO("Book:", service.add(dto) );
            return new ResponseEntity<ResponseDTO>(responseDto, HttpStatus.OK);
        }
 }

