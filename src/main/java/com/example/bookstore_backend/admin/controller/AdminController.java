package com.example.bookstore_backend.admin.controller;


import com.example.bookstore_backend.admin.dto.AdminDto;
import com.example.bookstore_backend.admin.exception.AdminException;
import com.example.bookstore_backend.admin.service.IAdminService;
import com.example.bookstore_backend.admin.utility.AdminResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	IAdminService iAdminService;

	@PostMapping("/login")
	public ResponseEntity<AdminResponse> login(@RequestBody AdminDto adminDto) throws AdminException {
		ResponseEntity<AdminResponse> response = new ResponseEntity<AdminResponse>(iAdminService.login(adminDto),
				HttpStatus.OK);
		return response;
	}
	
}


