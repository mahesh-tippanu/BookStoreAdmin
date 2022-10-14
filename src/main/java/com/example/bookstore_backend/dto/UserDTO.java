package com.example.bookstore_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    public long user_Id;
    public String userName;
    public String password;
    public String emailId;



}
