package com.jb.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.jb.erp.model.User;
import com.jb.erp.util.encryptUtils;

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
	
	public User findUserLogin(String login, String senha) {
		   try {
		        Query query = manager.createQuery("from User where deletado is false and login = :login and senha = :senha");
		        query.setParameter("login", login);
		        query.setParameter("senha", encryptUtils.encryptPasswordMD5(senha));

		        return (User) query.getSingleResult();
		    } catch (NoResultException e) {
		        return null;
		    }
	}
		
	public User save(User user) {
		return manager.merge(user);
	}

	public User delete(User user) {
		user.setDeletado(true);
		return save(user);
	}
}
