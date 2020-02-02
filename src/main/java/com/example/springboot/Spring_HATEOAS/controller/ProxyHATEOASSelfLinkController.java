package com.example.springboot.Spring_HATEOAS.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.Spring_HATEOAS.dto.CustomerDto;
import com.example.springboot.Spring_HATEOAS.dto.OrdersDto;
import com.example.springboot.Spring_HATEOAS.service.CustomerService;
import com.example.springboot.Spring_HATEOAS.service.OrdersService;

@RestController
@RequestMapping(value = "/proxy")
public class ProxyHATEOASSelfLinkController {

	static final Logger logger = LoggerFactory.getLogger(ProxyHATEOASSelfLinkController.class);

	@Autowired
	CustomerService customerService;
	
	@Autowired
	OrdersService ordersService;

	@GetMapping(value = "/customers", produces = { "application/hal+json" })
	public ResponseEntity<List<CustomerDto>> getCustomers() {
		List<CustomerDto> allCustomers = customerService.retrieveCustomers(null, null);
		for (CustomerDto customer : allCustomers)
	        customer.add(WebMvcLinkBuilder.linkTo(CustomerController.class).slash(customer.getId()).withSelfRel());
		return new ResponseEntity<List<CustomerDto>>(allCustomers, HttpStatus.OK);
	}
	
	@GetMapping(value = "/orders", produces = { "application/hal+json" })
	public ResponseEntity<List<OrdersDto>> getOrders() {
		List<OrdersDto> list = ordersService.retrieveOrders(null, null);
		for (OrdersDto order : list)
	        order.add(WebMvcLinkBuilder.linkTo(OrderController.class).slash(order.getId()).withSelfRel());
		return new ResponseEntity<List<OrdersDto>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/customers-with-orders", produces = { "application/hal+json" })
	public ResponseEntity<List<CustomerDto>> getCustomersWithOrders() {
		List<CustomerDto> allCustomers = customerService.retrieveCustomers(null, null);
		for (CustomerDto customer : allCustomers) {
	        customer.add(WebMvcLinkBuilder.linkTo(CustomerController.class).slash(customer.getId()).withSelfRel());
			customer.add(WebMvcLinkBuilder.linkTo(OrderController.class).slash("/orders").withRel("orders"));
			List<OrdersDto> list = ordersService.retrieveOrders(customer.getId(), null);
			for (OrdersDto order : list)
				customer.add(WebMvcLinkBuilder.linkTo(OrderController.class).slash(order.getId()).withSelfRel());
		}
		return new ResponseEntity<List<CustomerDto>>(allCustomers, HttpStatus.OK);
	}
	
	@GetMapping(value = "/orders-by-customer-id/{customerId}", produces = { "application/hal+json" })
	public ResponseEntity<List<OrdersDto>> getOrdersByCustomerId(@PathVariable String customerId) {
		List<OrdersDto> list = ordersService.retrieveOrders(Integer.parseInt(customerId), null);
		for (OrdersDto order : list)
	        order.add(WebMvcLinkBuilder.linkTo(OrderController.class).slash(order.getId()).withSelfRel());
		return new ResponseEntity<List<OrdersDto>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/orders-by-customer-name/{customerName}", produces = { "application/hal+json" })
	public ResponseEntity<List<OrdersDto>> getOrdersByCustomerName(@PathVariable String customerName) {
		List<OrdersDto> list = ordersService.retrieveOrders(null, customerName);
		for (OrdersDto order : list) 
	        order.add(WebMvcLinkBuilder.linkTo(OrderController.class).slash(order.getId()).withSelfRel());
		return new ResponseEntity<List<OrdersDto>>(list, HttpStatus.OK);
	}
}