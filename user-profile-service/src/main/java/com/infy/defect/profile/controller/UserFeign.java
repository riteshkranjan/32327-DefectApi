package com.infy.defect.profile.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infy.defect.commons.dto.UserDto;

@FeignClient("auth-service")
public interface UserFeign {
	
	@RequestMapping(value="/auth/profile/{userId}")
	UserDto getProfile(@PathVariable("userId") String userId);

}
