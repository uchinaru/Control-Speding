package com.jb.erp.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jb.erp.model.User;
import com.jb.erp.util.MessagesUtils;
import com.jb.erp.util.ServiceUtils;

@Named
@ViewScoped
public class LoginForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ServiceUtils serviceUtils;
	
	@Inject
	private MessagesUtils messagesUtils;
	
	private User usuario;
	private String login ="";
	private String senha ="";
	
	public void logar() {
		
		 usuario = serviceUtils.findLoginUser(login, senha);
		
		 if (usuario != null) {
			 messagesUtils.info("Login efetuado com sucesso!");
			 
			 usuario.setUltimoLogin(new Date);
			 serviceUtils.salvarUser(usuario);
		}else {
			messagesUtils.error("Erro ao logar");
		}
	}
	
	public String cadastrar() {
		return "CadastroDeUsuario";
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
