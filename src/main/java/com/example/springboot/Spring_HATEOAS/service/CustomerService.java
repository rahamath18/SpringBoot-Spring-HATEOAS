package com.example.springboot.Spring_HATEOAS.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.Spring_HATEOAS.dao.CustomerDao;
import com.example.springboot.Spring_HATEOAS.domain.Customer;
import com.example.springboot.Spring_HATEOAS.dto.CustomerDto;

@Service("customerService")
@Transactional
public class CustomerService {

	static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	CustomerDao customerDao;

	public void addCustomer(String customerName) {
		logger.debug("CustomerService|addCustomer...");
		Customer Customer = new Customer();
		Customer.setCustomerName(customerName);
		Customer.setStatus("ACTIVE");
		Customer.setVersion(1);
		Customer.setLogicallyDeleted("N");

		if (Customer.getId() == null)
			Customer.setCreatedBy(Customer.getCustomerName());
		else
			Customer.setUpdatedBy(Customer.getCustomerName());

		if (Customer.getId() == null)
			Customer.setCreatedDate(new Date());
		else
			Customer.setUpdatedDate(new Date());

		customerDao.addCustomer(Customer);
		logger.debug("CustomerService|addCustomer|after add " + Customer);
	}
	
	public List<CustomerDto> retrieveCustomers(Integer customerId, String customerName) {
		System.out.println(" ### customerId : " + customerId);
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT ").append(" CUS.ID as id, CUS.CUSTOMER_NAME as customerName, ")
				.append(" CUS.STATUS as status, CUS.VERSION as version, CUS.LOGICALLY_DELETED as logicallyDeleted, ")
				.append(" CUS.CREATED_DATE as createdDate, CUS.CREATED_BY as createdBy, ")
				.append(" CUS.UPDATED_DATE as updatedDate, CUS.UPDATED_BY as updatedBy ")
				.append(" FROM ORDER_SYSTEM.CUSTOMER CUS ")
				.append(" WHERE 1 = 1 ");

		if (customerId != null)
			sql.append(" AND CUS.ID = " + customerId);

		if (!StringUtils.isEmpty(StringUtils.trimToEmpty(customerName)))
			sql.append(" AND CUS.CUSTOMER_NAME = '" + customerName + "'");

		customerDao.flush();

		logger.debug(" ### query: " + sql.toString());

		Session session = customerDao.getSession();

		List<Object[]> list = session.createNativeQuery(sql.toString()).list();
		List<CustomerDto> resultList = new ArrayList<CustomerDto>();
		for (Object[] objects : list) 
			resultList.add(new CustomerDto((Integer) objects[0], (String) objects[1],
					(String) objects[2], (Integer) objects[3], (String) objects[4],
					(Timestamp) objects[5], (String) objects[6], (Timestamp) objects[7], (String) objects[8]));
		
		return resultList;
	}

	public Customer retrieveCustomerByCustomerName(String customerName) {
		return (Customer) customerDao.getNamedQuery("Customer.retrieveCustomerByCustomerName")
				.setParameter("customerName", customerName).uniqueResult();
	}

}
