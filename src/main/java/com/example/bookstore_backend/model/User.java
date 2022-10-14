package com.example.bookstore_backend.model;

import com.example.bookstore_backend.dto.LoginDTO;
import com.example.bookstore_backend.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false)
    private Long user_Id;
    private String userName;
    public String emailId;
    private String password;
    // private  String Token;

    public User(String encodedPassword, String userName, String emailId) {
        this.password = encodedPassword;
        this.userName = userName;
        this.emailId = emailId;
    }
    public Long getUser_Id() {
        return user_Id;
    }
    public String getPassword() {
        return password;
    }

    public void setUser_Id(Long user_Id) {
        this.user_Id = user_Id;
    }

    public String getEmailId() {
        return emailId;
    }

    public User(com.example.bookstore_backend.dto.UserDTO UserDTO) {
        this.userName = UserDTO.userName;
        this.emailId = UserDTO.emailId;
        this.password = UserDTO.password;
    }

    public User(LoginDTO loginDTO) {
        this.emailId = loginDTO.emailId;
        this.password = loginDTO.password;
    }

    public void updateData(UserDTO userDTO) {
        this.userName = userDTO.userName;
        this.emailId = userDTO.emailId;
        this.password = userDTO.password;
    }

}
