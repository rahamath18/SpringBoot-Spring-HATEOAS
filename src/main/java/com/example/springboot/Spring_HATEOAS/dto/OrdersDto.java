package com.example.springboot.Spring_HATEOAS.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

public class OrdersDto extends RepresentationModel<OrdersDto> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer customerId;

	private String customerName;

	private Double totalPrice;

	private String status;

	private Integer version;

	private String logicallyDeleted = "N";

	private Date createdDate;

	private String createdBy;

	private Date updatedDate;

	private String updatedBy;
	
	public OrdersDto() {}
	
	public OrdersDto(Integer id, Integer customerId, String customerName, Double totalPrice, String status,
			Integer version, String logicallyDeleted, Date createdDate, String createdBy, Date updatedDate,
			String updatedBy) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.customerName = customerName;
		this.totalPrice = totalPrice;
		this.status = status;
		this.version = version;
		this.logicallyDeleted = logicallyDeleted;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getLogicallyDeleted() {
		return logicallyDeleted;
	}

	public void setLogicallyDeleted(String logicallyDeleted) {
		this.logicallyDeleted = logicallyDeleted;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}