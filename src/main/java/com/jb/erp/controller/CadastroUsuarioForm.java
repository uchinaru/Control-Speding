package com.jb.erp.controller;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jb.erp.model.TipoUsuario;
import com.jb.erp.model.User;
import com.jb.erp.util.DateUtils;
import com.jb.erp.util.MessagesUtils;
import com.jb.erp.util.ServiceUtils;
import com.jb.erp.util.SessionUtils;
import com.jb.erp.util.encryptUtils;

@Named(value = "cadastroUser")
@ViewScoped
public class CadastroUsuarioForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceUtils serviceUtils;
	
	@Inject
	private MessagesUtils messagesUtils;
	
	@Inject
	private SessionUtils sessionUtils;
	
	@Inject
	private DateUtils dateUtils;
	
	private String nomeUser;
	private String loginUser;
	private String senhaUser;
	private String emailUser;
	private Date dataUser;
	
	public void voltarLogin() {
		sessionUtils.redirect("Login.xhtml");
	}
	
	public void cadastro() {
		if (validate()) {
			
			try {
				User user = new User();
				user.setNome(nomeUser);
				user.setLogin(loginUser);
				user.setSenha(encryptUtils.encryptPasswordMD5(senhaUser));
				user.setEmail(emailUser);
				user.setDataAniversario(dataUser);
				user.setTipoUsuario(TipoUsuario.B);
				user.setDeletado(false);

				serviceUtils.salvarUser(user);

				messagesUtils.info("Registro salvo com sucesso");
			} catch (Exception e) {
				messagesUtils.error("Erro ao salvar o registro");
			}
			
		}
	}

	private boolean validate() {
		
		if("".equals(nomeUser)) {
			messagesUtils.error("Preencha o campo Nome!");
			return false;
		}

		if("".equals(loginUser)) {
			messagesUtils.error("Preencha o campo Login!");
			return false;
		}
		
		if("".equals(senhaUser)) {
			messagesUtils.error("Preencha o campo Senha!");
			return false;
		}
		
		if("".equals(emailUser)) {
			messagesUtils.error("Preencha o campo E-mail!");
			return false;
		}
		
		int resultData = dateUtils.transformaDataSimples(dataUser).compareTo(dateUtils.transformaDataSimples(new Date()));
		
		if (resultData == 0) {
			messagesUtils.error("Data inválida");
			return false;
		}
		
		if (resultData > 0) {
			messagesUtils.error("Data inválida");
			return false;
		}
		
		return true;
	}

	public String getNomeUser() {
		return nomeUser;
	}

	public void setNomeUser(String nomeUser) {
		this.nomeUser = nomeUser;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getSenhaUser() {
		return senhaUser;
	}

	public void setSenhaUser(String senhaUser) {
		this.senhaUser = senhaUser;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public Date getDataUser() {
		return dataUser;
	}

	public void setDataUser(Date dataUser) {
		this.dataUser = dataUser;
	}

}
