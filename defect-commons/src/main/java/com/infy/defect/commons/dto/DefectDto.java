package com.infy.defect.commons.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DefectDto {
	private String defectId;
	private String prodName;
	private String defectDetails;
	private String status;
	private Integer yearOfPurchase;
	private Date insertDate;
	private String insertedBy;
}
