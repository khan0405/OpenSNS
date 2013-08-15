package com.khan.opensns.dao;

import java.io.Serializable;
import java.util.Collection;

public interface GenericDao<T, PK extends Serializable> {
	public T save(T entity);
	
	public Collection<T> save(Collection<T> entity);
	
	public T getById(PK id);
	
	public void delete(T entity);
	
	public void delete(PK id);
}
