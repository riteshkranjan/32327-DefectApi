package com.infy.defect.defect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.defect.defect.entity.Defect;

public interface DefectRepo extends JpaRepository<Defect, String> {
	
	List<Defect> findByInsertedBy(String insertedBy);
	

}
