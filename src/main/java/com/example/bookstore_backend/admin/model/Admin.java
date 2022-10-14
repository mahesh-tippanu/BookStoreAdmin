package com.example.bookstore_backend.admin.model;


import com.example.bookstore_backend.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Admin_Id")
	private Integer id;
	@Column(name="Admin_email")
	private String email;
	@Column(name="Admin_password")
	private String password;
	
	@OneToMany
	@JsonIgnore
	List<User> user;
	

}
