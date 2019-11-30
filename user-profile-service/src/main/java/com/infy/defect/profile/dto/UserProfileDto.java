package com.infy.defect.profile.dto;

import java.util.List;

import com.infy.defect.commons.dto.DefectDto;
import com.infy.defect.commons.dto.UserDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserProfileDto extends UserDto {

	private List<DefectDto> defects;

}
