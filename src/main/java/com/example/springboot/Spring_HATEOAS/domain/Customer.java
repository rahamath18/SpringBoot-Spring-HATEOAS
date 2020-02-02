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
@Table(name="CUSTOMER")
@NamedQueries ({ 
		@NamedQuery(name = "Customer.retrieveCustomerById", query = "from Customer where id = :id"),
		@NamedQuery(name = "Customer.retrieveCustomerByCustomerName", query = "from Customer where customerName = :customerName"),
})
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", precision=19)
	private Integer id;
	
	@Column(name = "CUSTOMER_NAME", unique = true, nullable = false, length = 20)
	private String customerName;
	
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

	public Customer() {
		super();
	}

	public Customer(Integer id, String customerName, String status, Integer version, String logicallyDeleted,
			Date createdDate, String createdBy, Date updatedDate, String updatedBy) {
		super();
		this.id = id;
		this.customerName = customerName;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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