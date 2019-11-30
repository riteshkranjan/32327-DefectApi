package com.infy.defect.defect.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
public class Defect {

	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(updatable = false, nullable = false)
	private String defectId;
	@NotBlank(message = "product name is mandatory")
	@Column(nullable = false)
	private String prodName;
	@NotBlank(message = "defect details is mandatory")
	@Column(nullable = false)
	private String defectDetails;
	@Column(nullable = false)
	private String status;
	private Integer yearOfPurchase;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date insertDate;
	@Column(nullable = false)
	@NotBlank(message = "User id is mandatory")
	private String insertedBy;

}
