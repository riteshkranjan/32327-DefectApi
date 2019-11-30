package com.infy.defect.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.infy.defect.auth.dto.LoginRequestDto;
import com.infy.defect.auth.entity.Users;
import com.infy.defect.auth.service.UsersService;
import com.infy.defect.commons.dto.UserDto;
import com.infy.defect.commons.exception.DefectAppException;

@RestController
@RequestMapping("/auth")
@Validated
public class UserController {

	@Autowired
	private UsersService service;

	@PostMapping("/register")
	public boolean register(@Valid @RequestBody Users u) throws DefectAppException {
		return service.registerUser(u);
	}

	@PostMapping("/login")
	public boolean login(@RequestBody LoginRequestDto l) {
		return service.loginUser(l.getUserId(), l.getPassword());
	}

	@GetMapping("/profile/{userId}")
	public UserDto getProfile(@PathVariable String userId) {
		return service.getProfile(userId);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.OK)
	public String handleIOException(MethodArgumentNotValidException ex, HttpServletRequest request) {
		StringBuilder sb = new StringBuilder("Invalid request : ");
		if (ex.getBindingResult() != null && ex.getBindingResult().getFieldError() != null) {
			sb.append(ex.getBindingResult().getFieldError().getField()).append(" : ")
					.append(ex.getBindingResult().getFieldError().getDefaultMessage());
		}
		return sb.toString();
	}
	
	@ExceptionHandler(DefectAppException.class)
	@ResponseStatus(value = HttpStatus.OK)
	public String handleIOException(DefectAppException ex, HttpServletRequest request) {
		return ex.getMessage();
	}

}
