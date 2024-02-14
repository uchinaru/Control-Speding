package com.jb.erp.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jb.erp.model.TipoUsuario;
import com.jb.erp.model.User;
import com.jb.erp.util.ServiceUtils;
import com.jb.erp.util.encryptUtils;

@Named(value = "cadastroUser")
@ViewScoped
public class CadastroUsuarioForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceUtils serviceUtils;
	
	private User user;
	
    @PostConstruct
    public void init() {
        prepararNewUser();
    }
    
	public void prepararNewUser() {
		user = new User();
	}
	
	public String voltarLogin() {
		return "Login";
	}
	
	public void cadastro() {
		try {
			user.setSenha(encryptUtils.encryptPasswordMD5(user.getSenha()));
			user.setTipoUsuario(TipoUsuario.B);
			user.setDeletado(false);

			serviceUtils.salvarUser(user);
			prepararNewUser();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro salvo", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar o registro ", ""));
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
