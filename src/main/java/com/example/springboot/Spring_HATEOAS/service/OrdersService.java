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
import com.example.springboot.Spring_HATEOAS.dao.OrdersDao;
import com.example.springboot.Spring_HATEOAS.domain.Customer;
import com.example.springboot.Spring_HATEOAS.domain.Orders;
import com.example.springboot.Spring_HATEOAS.dto.OrdersDto;

@Service("orderService")
@Transactional
public class OrdersService {

	static final Logger logger = LoggerFactory.getLogger(OrdersService.class);

	@Autowired
	CustomerDao customerDao;

	@Autowired
	OrdersDao ordersDao;

	public void addOrders(Customer customer, String totalPrice) {
		logger.debug("OrdersService|addOrders...");
		Orders orders = new Orders();
		orders.setCustomer(customerDao.getCustomer(customer.getId()));
		orders.setTotalPrice(Double.parseDouble(totalPrice));
		orders.setStatus("ACTIVE");
		orders.setVersion(1);
		orders.setLogicallyDeleted("N");

		if (orders.getId() == null)
			orders.setCreatedBy(customer.getCustomerName());
		else
			orders.setUpdatedBy(customer.getCustomerName());

		if (orders.getId() == null)
			orders.setCreatedDate(new Date());
		else
			orders.setUpdatedDate(new Date());

		ordersDao.addOrders(orders);
		logger.debug("ordersService|addorders|after add " + orders);

	}

	public List<OrdersDto> retrieveOrders(Integer customerId, String customerName) {
		System.out.println(" ### customerId : " + customerId);
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT ").append(" ORD.ID as id, CUS.ID as customerId, CUS.CUSTOMER_NAME as customerName, ")
				.append(" ORD.TOTAL_PRICE as totalPrice, ORD.STATUS as status, ")
				.append(" ORD.VERSION as version, ORD.LOGICALLY_DELETED as logicallyDeleted, ")
				.append(" ORD.CREATED_DATE as createdDate, ORD.CREATED_BY as createdBy, ")
				.append(" ORD.UPDATED_DATE as updatedDate, ORD.UPDATED_BY as updatedBy ")
				.append(" FROM ORDER_SYSTEM.ORDERS ORD ")
				.append(" JOIN ORDER_SYSTEM.CUSTOMER CUS ON (ORD.CUSTOMER_ID = CUS.ID) ")
				.append(" WHERE 1 = 1 ");

		if (customerId != null)
			sql.append(" AND ORD.CUSTOMER_ID = " + customerId);

		if (!StringUtils.isEmpty(StringUtils.trimToEmpty(customerName)))
			sql.append(" AND CUS.CUSTOMER_NAME = '" + customerName + "'");

		ordersDao.flush();

		logger.debug(" ### query: " + sql.toString());

		Session session = ordersDao.getSession();

		List<Object[]> list = session.createNativeQuery(sql.toString()).list();
		List<OrdersDto> resultList = new ArrayList<OrdersDto>();
		for (Object[] objects : list) 
			resultList.add(new OrdersDto((Integer) objects[0], (Integer) objects[1], (String) objects[2],
					(Double) objects[3], (String) objects[4], (Integer) objects[5], (String) objects[6],
					(Timestamp) objects[7], (String) objects[8], (Timestamp) objects[9], (String) objects[10]));

		return resultList;
	}

}
