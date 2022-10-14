package com.example.bookstore_backend.model;

import com.example.bookstore_backend.dto.AddressDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


    @Entity
    @Table(name="Address_Details")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Address {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int addressId;
        private String name;
        private String pincode;
        private String locality;
        private String address;
        private String city;
        private String landmark;
        private int type;


        @JsonIgnoreProperties(value= {"hibernateLazyInitializer","applications"})
        @OneToOne(fetch= FetchType.LAZY)
        @JoinColumn(name="user")
        private User user;

        public Address(AddressDTO addressDTO , User user) {
            this.name = addressDTO.getName();
            this.pincode = addressDTO.getPincode();
            this.locality = addressDTO.getLocality();
            this.address = addressDTO.getAddress();
            this.city = addressDTO.getCity();
            this.landmark = addressDTO.getLandmark();
            this.user=user;
   }
    }
