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
import com.example.springboot.Spring_HATEOAS.dto.CustomerDto;
import com.example.springboot.Spring_HATEOAS.service.CustomerService;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

	static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "/add-customer", method = RequestMethod.POST)
	public ResponseEntity<?> addCustomer(@RequestParam(value = "customerName") String customerName) {
		logger.debug("\n\n");
		logger.debug("addCustomer..." + customerName);

		if (StringUtils.isEmpty(StringUtils.trimToNull(customerName)))
			return new ResponseEntity<>("{\"message\":\"Customer Details Are Empty\"}", HttpStatus.OK);

		if (StringUtils.trim(customerName).length() < 8 || StringUtils.trim(customerName).length() > 20)
			return new ResponseEntity<>("{\"message\":\"Customer Name Length Min 8 and Max 20\"}", HttpStatus.OK);

		Customer cus = customerService.retrieveCustomerByCustomerName(customerName);

		if (cus != null && cus.getCustomerName().equalsIgnoreCase(customerName))
			return new ResponseEntity<>("{\"message\":\"Customer already exists\"}", HttpStatus.OK);

		customerService.addCustomer(customerName);

		return new ResponseEntity<>("{\"message\":\"Customer is added successfully\"}", HttpStatus.OK);
	}

	@GetMapping(value = "/{customerId}", produces = { "application/hal+json" })
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable String customerId) {
		return new ResponseEntity<CustomerDto>(customerService.retrieveCustomers(Integer.parseInt(customerId), null).get(0), HttpStatus.OK);
	}

	@GetMapping(value = "/customers", produces = { "application/hal+json" })
	public ResponseEntity<List<CustomerDto>> getCustomers() {
		return new ResponseEntity<List<CustomerDto>>(customerService.retrieveCustomers(null, null), HttpStatus.OK);
	}

}