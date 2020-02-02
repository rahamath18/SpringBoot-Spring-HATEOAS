package com.example.springboot.Spring_HATEOAS.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="ORDERS_ITEMS")
public class OrdersItems implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", precision = 19)
	private Integer id;

	@ManyToOne(optional = false)
	@NaturalId(mutable = true)
	@JoinColumn(name = "ORDERS_ID", nullable = false)
	private Orders orders;

	@ManyToOne(optional = false)
	@NaturalId(mutable = true)
	@JoinColumn(name = "ITEMS_ID", nullable = false)
	private Items items;

	@Column(name = "TOTAL_PRICE", nullable = true, precision = 10, scale = 3)
	private Double totalPrice;

	@Column(name = "QUANTITY", nullable = true, length = 7)
	private Integer quantity;

	public OrdersItems() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
