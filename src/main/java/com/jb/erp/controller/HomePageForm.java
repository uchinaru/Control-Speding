package com.jb.erp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.jb.erp.model.User;

@Named
@SessionScoped	
public class HomePageForm extends FormAbstract implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String userName;
	
	@PostConstruct
	public void init() {

		loadPageWhifUserLogged();
		userName = getUsuario().getLogin();
	}
	
	public void logout() {
		logoutUserApplication();
	}
	
	public void cadastrarDespesa() {
		redirectToPage("DespesasList.xhtml");
	}
	
	public String getUserName() {
		return userName;
	}


}
