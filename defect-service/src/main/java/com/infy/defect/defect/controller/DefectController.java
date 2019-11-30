package com.infy.defect.defect.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.infy.defect.commons.dto.DefectDto;
import com.infy.defect.commons.exception.DefectAppException;
import com.infy.defect.defect.entity.Defect;
import com.infy.defect.defect.service.DefectService;

@RestController
@Validated
@RequestMapping("/defect")
public class DefectController {

	@Autowired
	private DefectService service;

	@PostMapping
	public String addDefect(@Valid @RequestBody Defect d) {
		return service.addDefect(d);
	}

	@PutMapping("/{defectId}/{status}")
	public boolean updateDefect(@PathVariable String defectId, @PathVariable String status) {
		return service.updateDefect(defectId, status);
	}

	@GetMapping("/{defectId}")
	public DefectDto viewDefect(@PathVariable String defectId) {
		return service.viewDefect(defectId);
	}

	@GetMapping("/user/{userId}")
	public List<DefectDto> viewDefectById(@PathVariable String userId) {
		return service.viewDefectByUserId(userId);
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
