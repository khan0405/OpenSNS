package com.khan.opensns.api.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Criterion;

import com.khan.opensns.dao.GenericDao;

public interface BaseDao<T, PK extends Serializable> extends GenericDao<T, PK> {
	public T save(T entity);
	
	public Collection<T> save(Collection<T> entity);
	
	public T getById(PK id);
	
	public List<T> findByCriteria(Criterion criterion);

	public void delete(T entity);
	
	public void delete(PK id);
}
