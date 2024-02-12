package com.jb.erp.util;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.jb.erp.model.User;
import com.jb.erp.repository.UsersSearch;

public class ServiceUtils implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	UsersSearch userSearch;
	
	@Transactional
	public void salvarUser(User usuario) {
		userSearch.Save(usuario);
	}
	
	@Transactional
	public User findLoginUser(String login, String Senha) {
		
		return userSearch.findUserLogin(login, Senha);
	}
}
