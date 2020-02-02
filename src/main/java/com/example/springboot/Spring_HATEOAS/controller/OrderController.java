package com.example.springboot.Spring_HATEOAS.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.Spring_HATEOAS.domain.Customer;
import com.example.springboot.Spring_HATEOAS.dto.OrdersDto;
import com.example.springboot.Spring_HATEOAS.service.CustomerService;
import com.example.springboot.Spring_HATEOAS.service.OrdersService;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

	static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	CustomerService customerService;

	@Autowired
	OrdersService ordersService;

	@RequestMapping(value = "/add-order", method = RequestMethod.POST)
	public ResponseEntity<?> addOrder(@RequestParam(value = "customerName") String customerName,
			@RequestParam(value = "totalPrice") String totalPrice) {
		logger.debug("\n\n");
		logger.debug("addOrder...customerName = " + customerName + " | totalPrice = " + totalPrice);

		if (StringUtils.isEmpty(StringUtils.trimToNull(customerName)))
			return new ResponseEntity<>("{\"message\":\"Customer Details Are Empty\"}", HttpStatus.OK);

		if (StringUtils.trim(customerName).length() < 8 || StringUtils.trim(customerName).length() > 20)
			return new ResponseEntity<>("{\"message\":\"Customer Name Length Min 8 and Max 20\"}", HttpStatus.OK);

		Customer customer = customerService.retrieveCustomerByCustomerName(customerName);

		if (customer == null || StringUtils.isEmpty(StringUtils.trimToNull(customer.getCustomerName())))
			return new ResponseEntity<>("{\"message\":\"Customer does not exists\"}", HttpStatus.OK);

		if (StringUtils.isEmpty(StringUtils.trimToNull(totalPrice)))
			return new ResponseEntity<>("{\"message\":\"Order Details Are Empty\"}", HttpStatus.OK);

		if (Double.parseDouble(totalPrice) > 50000)
			return new ResponseEntity<>("{\"message\":\"Order's Total Price Can Not Exceed 50000\"}", HttpStatus.OK);

		ordersService.addOrders(customer, totalPrice);

		return new ResponseEntity<>("{\"message\":\"Order is added successfully\"}", HttpStatus.OK);
	}

	@GetMapping(value = "/{orderId}", produces = { "application/hal+json" })
	public ResponseEntity<OrdersDto> getOrderById(@PathVariable String orderId) {
		return new ResponseEntity<OrdersDto>(ordersService.retrieveOrders(Integer.parseInt(orderId), null).get(0),
				HttpStatus.OK);
	}

	@GetMapping(value = "/orders", produces = { "application/hal+json" })
	public ResponseEntity<List<OrdersDto>> getOrders() {
		return new ResponseEntity<List<OrdersDto>>(ordersService.retrieveOrders(null, null), HttpStatus.OK);
	}

}