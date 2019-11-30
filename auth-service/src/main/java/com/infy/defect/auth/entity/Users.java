package com.infy.defect.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
public class Users {

	@Id
	@GeneratedValue
	private Integer id;
	@Column(nullable = false)
	@NotBlank(message = "user id is mandatory")
	private String userId;
	@NotBlank(message = "name is mandatory")
	@Column(nullable = false)
	private String name;
	@Email
	@NotBlank(message = "email id is mandatory")
	private String emailId;
	@Length(min = 8)
	private String password;
	private Long phoneNumber;
	private String address;

}
