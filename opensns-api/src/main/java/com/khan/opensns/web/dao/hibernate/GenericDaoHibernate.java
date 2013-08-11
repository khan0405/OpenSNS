package com.khan.opensns.web.dao.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.khan.opensns.dao.GenericDao;

public class GenericDaoHibernate<T, PK extends Serializable> implements GenericDao<T, PK> {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Class<T> entityClass;
	
	public GenericDaoHibernate(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	public Class<T> getEntity() {
		return entityClass;
	}
	
	@Transactional
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
	
	@Override
	public T save(T entity) {
		getSession().saveOrUpdate(entity);
		getSession().flush();
		return entity;
	}
	
	@Override
	public Collection<T> save(Collection<T> entity) {
		getSession().saveOrUpdate(entity);
		getSession().flush();
		return entity;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getById(PK id) {
		return (T) getSession().get(getEntity(), id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(Criterion criterion) {
		Criteria criteria = getSession().createCriteria(getEntity());
		criteria.add(criterion);
		return (List<T>) criteria.list();
	}

	@Override
	public void delete(T entity) {
		getSession().delete(entity);
	}
	
	@Override
	public void delete(PK id) {
		delete(getById(id));
	}
	
}