package com.example.bookstore_backend.admin.utility;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminResponse {
	
	private String responseStatus;
	private int responseCode;
	private Object responseData;
}
