package com.jb.erp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jb.erp.model.User;

@Named
@SessionScoped	
public class HomePageForm extends FormAbstract implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private User usuario;
	
	private String userName;
	private List<User> users = new ArrayList<User>();
	
	@PostConstruct
	public void init() {
		try {
			Long idUser = userLogged();
			if (idUser != null) {
				usuario = getServiceUserUtils().findById(idUser);
				userName = usuario.getLogin();
			}else {
				redirectToPage("Login.xhtml");
				}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void logout() {
		logoutUserApplication();
	}
	
	public void logoutIdle() {
		logout();
	}
	
	public void voltar() {
		redirectToPage("HomePage.xhtml");
	}
	
	public void cadastrarDespesa() {
		redirectToPage("DespesasList.xhtml");
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
