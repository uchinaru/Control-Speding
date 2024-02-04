package com.jb.erp.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jb.erp.model.User;
import com.jb.erp.repository.UsersSearch;
import com.jb.erp.util.encryptUtils;

@Named
@ViewScoped
public class LoginForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsersSearch userSearch;
	
	private User usuario;
	private String login ="";
	private String senha ="";
	
	public void logar() {
		
		 usuario = userSearch.findUser(login, encryptUtils.encryptPasswordMD5(senha));
		
		 if (usuario != null) {
			 System.out.println("LOGOU !");
			 FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Login efetuado com sucesso","Logado"));
		}else {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario não encontrado!","Erro no Login!"));
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
}
