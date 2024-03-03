package com.jb.erp.controller;

import java.io.Serializable;
import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.jb.erp.model.User;
import com.jb.erp.util.DateUtils;
import com.jb.erp.util.MessagesUtils;
import com.jb.erp.util.ServiceUtils;
import com.jb.erp.util.SessionUtils;


@Named
@ViewScoped
public class LoginForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ServiceUtils serviceUtils;
	
	@Inject
	private MessagesUtils messagesUtils;
	
	@Inject
	private SessionUtils sessionUtils;
	
	@Inject
	private DateUtils dateUtils;
	
	private User usuario;
	private String login ="";
	private String senha ="";
	
	public void logar() {
		
		 usuario = serviceUtils.findLoginUser(login, senha);
		
		 if (usuario != null) {
			 processaUsuario();
			 
			 HttpSession session = sessionUtils.createSession();
			 session.setAttribute("userId", usuario.getId());
			 
			 sessionUtils.redirect("HomePage.xhtml");
		}else {
			messagesUtils.error("Erro ao logar");
		}
	}
	
	private void processaUsuario() {
		 usuario.setOnline(true);
		 usuario.setDataUltimoLogin(new Date());
		 usuario.setDataAniversario(dateUtils.transformaDataSimples(usuario.getDataAniversario()));
		 serviceUtils.salvarUser(usuario);
	}

	public void cadastrar() {
		sessionUtils.redirect("CadastroDeUsuario.xhtml");
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
