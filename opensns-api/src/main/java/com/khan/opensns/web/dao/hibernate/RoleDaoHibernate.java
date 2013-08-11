package com.khan.opensns.web.dao.hibernate;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.khan.opensns.dao.RoleDao;
import com.khan.opensns.model.Role;

@Repository
public class RoleDaoHibernate extends GenericDaoHibernate<Role, Integer> implements RoleDao {
	
	public RoleDaoHibernate() {
		super(Role.class);
	}
	
	@Override @SuppressWarnings("unchecked")
	public List<Role> getAll() {
		return getSession().createCriteria(getEntity()).list();
	}
	
	@Override @SuppressWarnings("unchecked")
	public Role getRoleByName(String name) {
		List<Role> roles = (List<Role>)getSession().createQuery("from Role r where r.name = :name")
				.setString("name", name)
				.setCacheable(true)
				.list();
		if (roles == null || roles.isEmpty()) {
			throw new UsernameNotFoundException("Role '" + name + "' not found");
		}
		return roles.get(0);
	}

	@Override
	public void deleteRole(String name) {
		delete(getRoleByName(name));
	}

}
