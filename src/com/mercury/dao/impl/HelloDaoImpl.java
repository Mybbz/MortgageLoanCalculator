package com.mercury.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.mercury.beans.InterestRate;
import com.mercury.beans.User;
import com.mercury.dao.HelloDao;

public class HelloDaoImpl implements HelloDao {
	private HibernateTemplate template;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		template = new HibernateTemplate(sessionFactory);
	}
	
	@Override
	public String findByName(String name) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("username", name));
		List result = template.findByCriteria(criteria);
		if(!result.isEmpty()) return "true";
		else return "false";
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		template.save(user);
	}

	@Override
	public double findByYear(int year) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(InterestRate.class);
        criteria.add(Restrictions.eq("term",year));
        System.out.println(year);
		List result = template.findByCriteria(criteria);
		InterestRate ir = new InterestRate();
		ir = (InterestRate) result.get(0);
		return ir.getRate();
	}

	

}
