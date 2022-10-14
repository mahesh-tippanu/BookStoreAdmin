package com.example.bookstore_backend.admin.service;

import com.example.bookstore_backend.admin.dto.AdminDto;
import com.example.bookstore_backend.admin.exception.AdminException;
import com.example.bookstore_backend.admin.utility.AdminResponse;

public interface IAdminService {

	AdminResponse login(AdminDto adminDto) throws AdminException;
}
