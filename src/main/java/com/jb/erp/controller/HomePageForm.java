package com.jb.erp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jb.erp.model.User;
import com.jb.erp.repository.UserSession;
import com.jb.erp.repository.UsersSearch;

@ViewScoped
@Named
public class HomePageForm implements Serializable{
	private static final long serialVersionUID = 1L;

	
	@Inject
	private UserSession userSession;
	
	@Inject
	private User usuario;
	
	@Inject
	private UsersSearch userSearch;
	
	private String userName;
	private List<User> users = new ArrayList<User>();
	
	@PostConstruct
	public void init() {
		usuario = userSearch.findUserFromId(userSession.getUserId());
		userName = usuario.getLogin();
	}
	
	public String logout() {
		userSession.deslogarUsuario();
		return "Login";
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
