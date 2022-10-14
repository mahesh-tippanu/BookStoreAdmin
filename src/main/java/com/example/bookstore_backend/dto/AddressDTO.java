package com.example.bookstore_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


    @Data
    @AllArgsConstructor
    public class AddressDTO {
        private long userId;
        private String name;
        private String pincode;
        private String locality;
        private String address;
        private String city;
        private String landmark;
        private  int type;

    }

