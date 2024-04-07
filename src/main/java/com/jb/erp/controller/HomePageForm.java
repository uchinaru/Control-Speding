package com.jb.erp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jb.erp.model.User;
import com.jb.erp.repository.UserSession;
import com.jb.erp.repository.UsersSearch;
import com.jb.erp.util.DateUtils;
import com.jb.erp.util.MessagesUtils;
import com.jb.erp.util.ServiceUtils;

@Named
@SessionScoped	
public class HomePageForm implements Serializable{
	private static final long serialVersionUID = 1L;

	
	@Inject
	private UserSession userSession;
	
	@Inject
	private User usuario;
	
	@Inject
	private UsersSearch userSearch;
	
	@Inject
	private ServiceUtils serviceUtils;
	
	@Inject
	private DateUtils dateUtils;
	
	@Inject
	private MessagesUtils messagesUtils;
	
	private String userName;
	private List<User> users = new ArrayList<User>();
	
	@PostConstruct
	public void init() {
		try {
			Long idUser = userSession.getUserId();
			if (idUser != null) {
				usuario = userSearch.findUserFromId(idUser);
				userName = usuario.getLogin();
			}else {
				userSession.redirectUserNotLogged("Login.xhtml");
				}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void logout() {
		processaUsuario();
		userSession.deslogarUsuario();
		userSession.redirectUserNotLogged("Login.xhtml");
	}
	
	private void processaUsuario() {
		 usuario.setOnline(false);
		 serviceUtils.salvarUser(usuario);
	}
	
	public void logoutIdle() {
		logout();
	}
	
	public void cadastrarDespesa() {
		userSession.redirectUserNotLogged("DespesasList.xhtml");
	}
	
	public String getUserName() {
		return userName;
	}

	public List<User> getUsers() {
		return users;
	}

	public User getUsuario() {
		return usuario;
	}
	
}
