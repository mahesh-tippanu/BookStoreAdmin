package com.example.bookstore_backend.admin.service;

import com.example.bookstore_backend.admin.dto.AdminDto;
import com.example.bookstore_backend.admin.exception.AdminException;
import com.example.bookstore_backend.admin.model.Admin;
import com.example.bookstore_backend.admin.repository.AdminRepository;
import com.example.bookstore_backend.admin.utility.AdminResponse;
import com.example.bookstore_backend.admin.utility.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService implements IAdminService {

	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	EmailSender emailSender;

	@Override
	public AdminResponse login(AdminDto adminDto) throws AdminException {
		Optional<Admin> isAdmin = adminRepository.findByEmail(adminDto.getEmail());
		if (isAdmin.isPresent()) {
			String password = isAdmin.get().getPassword();
			password.equals(adminDto.getPassword());
			return new AdminResponse("..Admin found at our Database..", 200, null);
		} else {
			throw new AdminException("Entered Email Id or Password is Wrong... Kindly check and Try again ");

		}
	}

}
