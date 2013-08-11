package com.khan.opensns.web.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.khan.opensns.dao.PropertyDao;
import com.khan.opensns.model.Property;

@Repository
public class PropertyDaoHibernate extends GenericDaoHibernate<Property, String> implements PropertyDao {

	public PropertyDaoHibernate() {
		super(Property.class);
	}
	
}
