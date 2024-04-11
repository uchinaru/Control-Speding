package com.jb.erp.util;

import java.io.Serializable;
import javax.inject.Inject;
import com.jb.erp.model.User;
import com.jb.erp.repository.UsersSearch;

public class ServiceUserUtils implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	UsersSearch userSearch;

	@Transacional
	public void saveUser(User usuario) {
		userSearch.save(usuario);
	}

	@Transacional
	public User findLoginUser(String login, String Senha) {

		return userSearch.findUserLogin(login, Senha);
	}
	
	@Transacional
	public User findById(Long id) {
		return userSearch.findUserFromId(id);
	}
}
