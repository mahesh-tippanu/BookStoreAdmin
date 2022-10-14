package com.example.bookstore_backend.service;

import com.example.bookstore_backend.dto.AddressDTO;
import com.example.bookstore_backend.exception.BookException;
import com.example.bookstore_backend.model.Address;
import com.example.bookstore_backend.model.User;
import com.example.bookstore_backend.repository.AddressRepo;
import com.example.bookstore_backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {


       @Autowired
       AddressRepo addressRepo;

        @Autowired
        UserRepo userRepo;

        public Address add(AddressDTO addressDTO) {
            Optional<User> user = userRepo.findById(addressDTO.getUserId());
            if (user.isPresent()) {
                Address address= new Address(addressDTO, user.get());
                return addressRepo.save(address);
            } else
                throw new BookException("User Not Found");
     }

}






