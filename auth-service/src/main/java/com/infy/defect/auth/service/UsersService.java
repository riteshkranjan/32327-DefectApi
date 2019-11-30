package com.infy.defect.auth.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.infy.defect.auth.entity.Users;
import com.infy.defect.auth.repository.UsersRepository;
import com.infy.defect.commons.dto.UserDto;
import com.infy.defect.commons.exception.DefectAppException;

@Service
public class UsersService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsersRepository repo;

	@Autowired
	private ModelMapper modelMapper;

	public boolean registerUser(Users u) throws DefectAppException {
		if (searchUser(u.getUserId()) != null) {
			throw new DefectAppException("User already exists");
		}
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		repo.save(u);
		return true;
	}

	private Users searchUser(String userId) {
		List<Users> user = repo.findByUserId(userId);
		return user.size() == 0 ? null : user.get(0);
	}

	public boolean loginUser(String userId, String password) {
		Users user = searchUser(userId);
		return user == null ? false : passwordEncoder.matches(password, user.getPassword());
	}

	public UserDto getProfile(String userId) {
		Users user = searchUser(userId);
		if (user == null)
			return null;
		UserDto dto = new UserDto();
		modelMapper.map(user, dto);
		return dto;
	}

}
