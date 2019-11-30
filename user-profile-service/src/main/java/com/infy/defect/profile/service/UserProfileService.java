package com.infy.defect.profile.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.infy.defect.commons.dto.DefectDto;
import com.infy.defect.commons.dto.UserDto;
import com.infy.defect.profile.controller.UserFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;

@Service
public class UserProfileService {

	@Autowired
	RestTemplate template;

	@Autowired
	UserFeign userService;

	@HystrixCommand(fallbackMethod = "getProfileFallBack")
	public Future<UserDto> getProfile(final String userId) {
		return new AsyncResult<UserDto>() {
			@Override

			public UserDto invoke() {
				return userService.getProfile(userId);
			}
		};
	}

	public UserDto getProfileFallBack(String userId) {
		System.out.println("in getProfileFallBack");
		return new UserDto();
	}

	@SuppressWarnings("unchecked")
	@HystrixCommand(fallbackMethod = "getDefectDetailsFallback")
	public Future<List<DefectDto>> getDefectDetails(String userId) {
		return new AsyncResult<List<DefectDto>>() {

			@Override
			public List<DefectDto> invoke() {
				return template.getForObject("http://defect-service/defect/user/" + userId, List.class);
			}
		};
	}

	public List<DefectDto> getDefectDetailsFallback(String userId) {
		System.out.println("in getDefectDetailsFallback");
		return new ArrayList<DefectDto>();
	}

}
