package com.jb.erp.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.jb.erp.model.User;

@Named
@ViewScoped
public class LoginForm extends FormAbstract implements Serializable {
	private static final long serialVersionUID = 1L;

	private User usuario;
	private String login ="";
	private String senha ="";
	
	public void logar() {

		if (validate()) {

			loginUserApplication(usuario);
			HttpSession session = getSessionUtils().createSession();
			session.setAttribute("userId", usuario.getId());

			redirectToPage("HomePage.xhtml");
		}
	}
	
	private boolean validate() {

		if ("".equals(login)) {
			getMessagesUtils().warning("Preencha o campo login!");
			return false;
		}

		if ("".equals(senha)) {
			getMessagesUtils().warning("Preencha o campo senha!");
			return false;
		}
		
		usuario = getServiceUserUtils().findLoginUser(login, senha);
		if (usuario == null ) {
			getMessagesUtils().error("Usuário não localizado, verifique o login e senha.");
			return false;
		}
		
		return true;
	}

	public void cadastrar() {
		redirectToPage("CadastroDeUsuario.xhtml");
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
