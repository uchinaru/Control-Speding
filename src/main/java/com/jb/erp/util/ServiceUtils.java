package com.jb.erp.util;

import java.io.Serializable;
import javax.inject.Inject;
import com.jb.erp.model.User;
import com.jb.erp.repository.UsersSearch;

public class ServiceUtils implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	UsersSearch userSearch;

	@Transacional
	public void salvarUser(User usuario) {
		userSearch.Save(usuario);
	}

	@Transacional
	public User findLoginUser(String login, String Senha) {

		return userSearch.findUserLogin(login, Senha);
	}
}
