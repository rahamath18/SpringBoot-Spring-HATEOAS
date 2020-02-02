package com.example.springboot.Spring_HATEOAS.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="ITEMS")
@NamedQueries({ @NamedQuery(name = "Items.retrieveItemsById", query = " from Items where id = :id") })
public class Items implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", precision = 19)
	private Integer id;

	@Column(name = "NAME", nullable = false, length = 100)
	private String name;
	
	@Column(name = "PRICE", nullable = true, precision = 10, scale = 3)
	private Double price;

	@Column(name = "BRAND", nullable = false, length = 50)
	private String brand;

	@Column(name = "COLOR", nullable = true, length = 12)
	private String color;

	@Column(name = "IMAGE", nullable = true, length = 120)
	private String image;

	@Column(name = "STATUS", nullable = false, length = 12)
	private String status;

	@Version
	@Column(name = "VERSION", nullable = false)
	private Integer version;

	@Column(name = "LOGICALLY_DELETED", nullable = false, length = 1)
	private String logicallyDeleted = "N";

	@Column(name = "CREATED_DATE", nullable = false)
	private Date createdDate;

	@Column(name = "CREATED_BY", nullable = false, length = 20)
	private String createdBy;

	@Column(name = "UPDATED_DATE", nullable = true)
	private Date updatedDate;

	@Column(name = "UPDATED_BY", nullable = true, length = 20)
	private String updatedBy;

	public Items() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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