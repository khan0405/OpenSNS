package com.khan.opensns.api.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.khan.opensns.api.dao.PropertyDao;
import com.khan.opensns.model.Property;

@Repository
public class PropertyDaoHibernate extends GenericDaoHibernate<Property, String> implements PropertyDao {

	public PropertyDaoHibernate() {
		super(Property.class);
	}
	
}
