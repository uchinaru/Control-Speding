package com.jb.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.jb.erp.model.User;

public class UsersSearch implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public UsersSearch() {
	}
	
	public UsersSearch(EntityManager manager) {
		this.manager = manager;
	}
	
	public User findUserFromId(Long id) {
		return manager.find(User.class, id);
	}

	public List<User> allUsers() {
		return manager.createQuery("from User", User.class).getResultList();
	}
		
	public void Save(User user) {
		manager.merge(user);
	}

	public void Delete(User user) {
		user.setDeletado(true);
		Save(user);
	}
}
