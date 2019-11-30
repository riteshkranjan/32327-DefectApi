package com.infy.defect.defect.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.defect.commons.dto.DefectDto;
import com.infy.defect.commons.dto.Status;
import com.infy.defect.defect.entity.Defect;
import com.infy.defect.defect.repository.DefectRepo;

@Service
public class DefectService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private DefectRepo repo;

	public String addDefect(Defect entity) {
		entity.setInsertDate(new Date());
		entity.setStatus(Status.Open.name());
		return repo.saveAndFlush(entity).getDefectId();
	}

	public boolean updateDefect(String defectId, String status) {
		Defect d = searchDefect(defectId);
		if (d == null)
			return false;
		d.setStatus(status);
		repo.save(d);
		return true;
	}

	public DefectDto viewDefect(String defectId) {
		Defect d = searchDefect(defectId);
		if (d == null)
			return null;
		DefectDto dto = new DefectDto();
		modelMapper.map(d, dto);
		return dto;
	}
	
	public List<DefectDto> viewDefectByUserId(String userId) {
		List<Defect> defectInDb = repo.findByInsertedBy(userId);
		List<DefectDto> defects = new ArrayList<>();
		for(Defect d : defectInDb) {
			DefectDto dto = new DefectDto();
			modelMapper.map(d, dto);
			defects.add(dto);
		}
		return defects;
		
	}

	private Defect searchDefect(String defectId) {
		Optional<Defect> defect = repo.findById(defectId);
		return defect.isPresent() ? defect.get() : null;
	}

}
