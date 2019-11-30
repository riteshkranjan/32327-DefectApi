package com.infy.defect.profile.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.defect.commons.dto.DefectDto;
import com.infy.defect.commons.dto.UserDto;
import com.infy.defect.profile.dto.UserProfileDto;
import com.infy.defect.profile.service.UserProfileService;

@RestController
@RequestMapping("profile")
public class UserProfileController {

	@Autowired
	private UserProfileService service;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("{userId}")
	public UserProfileDto viewProfile(@PathVariable String userId) throws InterruptedException, ExecutionException {
		Future<List<DefectDto>> defectDetails = service.getDefectDetails(userId);
		Future<UserDto> profile = service.getProfile(userId);
		UserProfileDto response = new UserProfileDto();
		modelMapper.map(profile.get(),response);
		response.setDefects(defectDetails.get());
		return response;
	}
}
