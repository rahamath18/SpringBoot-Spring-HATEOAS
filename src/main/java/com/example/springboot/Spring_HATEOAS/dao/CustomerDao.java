package com.example.springboot.Spring_HATEOAS.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springboot.Spring_HATEOAS.domain.Customer;

@Repository("customerDao")
public class CustomerDao {

	static final Logger logger = LoggerFactory.getLogger(CustomerDao.class);

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

	public Query getNamedQuery(String queryName) {
		return sessionFactory.getCurrentSession().getNamedQuery(queryName);
	}

	 

	public Customer getCustomer(int id) {
		return (Customer) sessionFactory.getCurrentSession().get(Customer.class, id);
	}

	public Customer addCustomer(Customer Customer) {
		logger.debug("CustomerDao|addCustomer...");
		try {
			sessionFactory.getCurrentSession().save(Customer);
		} catch (Exception ex) {
			logger.debug("CustomerDao|addCustomer...Exception....");
			ex.printStackTrace();
		}
		return Customer;
	}

	public void updateCustomer(Customer Customer) {
		Session session = sessionFactory.getCurrentSession();
		Hibernate.initialize(Customer);
		session.update(Customer);
	}

	public void deleteCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();
		Customer p = (Customer) session.load(Customer.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
	}

}
