package com.example.springboot.Spring_HATEOAS.dto;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

public class OrdersItemsDto extends RepresentationModel<OrdersDto> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private OrdersDto ordersDto;

	private ItemsDto items;

	private Double totalPrice;

	private Integer quantity;

	public OrdersItemsDto() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrdersDto getOrdersDto() {
		return ordersDto;
	}

	public void setOrdersDto(OrdersDto ordersDto) {
		this.ordersDto = ordersDto;
	}

	public ItemsDto getItems() {
		return items;
	}

	public void setItems(ItemsDto items) {
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
