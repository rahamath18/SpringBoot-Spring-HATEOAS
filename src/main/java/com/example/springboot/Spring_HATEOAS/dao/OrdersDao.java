package com.example.springboot.Spring_HATEOAS.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springboot.Spring_HATEOAS.domain.Orders;

@Repository("ordersDao")
public class OrdersDao {

	static final Logger logger = LoggerFactory.getLogger(OrdersDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		sessionFactory = sf;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void flush() {
		sessionFactory.getCurrentSession().flush();
	}

	public NativeQuery<Orders> createNativeQuery(String queryString) {
		return sessionFactory.getCurrentSession().createNativeQuery(queryString, Orders.class);
	}

	public Query getNamedQuery(String queryName) {
		return sessionFactory.getCurrentSession().getNamedQuery(queryName);
	}

	public List<Orders> getAllOrders() {
		return (List<Orders>) sessionFactory.getCurrentSession().createQuery("from Orders").list();
	}

	public Orders getOrders(int id) {
		return (Orders) sessionFactory.getCurrentSession().get(Orders.class, id);
	}

	public Orders addOrders(Orders Orders) {
		logger.debug("OrdersDao|addOrders...");
		try {
			sessionFactory.getCurrentSession().save(Orders);
		} catch (Exception ex) {
			logger.debug("OrdersDao|addOrders...Exception....");
			ex.printStackTrace();
		}
		return Orders;
	}

	public void updateOrders(Orders Orders) {
		Session session = sessionFactory.getCurrentSession();
		Hibernate.initialize(Orders);
		session.update(Orders);
	}

	public void deleteOrders(int id) {
		Session session = sessionFactory.getCurrentSession();
		Orders p = (Orders) session.load(Orders.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
	}

}
