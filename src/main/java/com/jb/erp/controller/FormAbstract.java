package com.jb.erp.controller;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import com.jb.erp.model.User;
import com.jb.erp.repository.UserSession;
import com.jb.erp.repository.UsersSearch;
import com.jb.erp.util.DateUtils;
import com.jb.erp.util.MessagesUtils;
import com.jb.erp.util.ServiceUserUtils;
import com.jb.erp.util.SessionUtils;

public class FormAbstract implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private SessionUtils sessionUtils;
	
	@Inject
	private UserSession userSession;
	
	@Inject
	private ServiceUserUtils serviceUserUtils;
	
	@Inject
	private UsersSearch usersSearch;
	
	@Inject
	private DateUtils dateUtils;
	
	@Inject
	private MessagesUtils messagesUtils;
	
	@Inject
	private User usuario;
	
	public Long userLogged() {
		return userSession.getUserId();
	}
	
	public void redirectToPage(String page) {
		sessionUtils.redirect(page);
	}
	
	public void sessionClose(){
		sessionUtils.sessionClose();
	}
	
	public void logoutUserApplication() {
		try {
			usuario = usersSearch.findUserFromId(userLogged());
			
			usuario.setOnline(false);
			serviceUserUtils.saveUser(usuario);
			sessionClose();
			redirectToPage("Login.xhtml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loginUserApplication(User user) {
		user.setOnline(true);
		user.setDataUltimoLogin(new Date());
		user.setDataAniversario(dateUtils.transformaDataSimples(user.getDataAniversario()));
		serviceUserUtils.saveUser(user);
	}
	
	public boolean loadPageWhifUserLogged() {
		if (userLogged() != null && userLogged() > 0) {
			usuario = usersSearch.findUserFromId(userLogged());
			return true;
		} else {
			redirectToPage("Login.xhtml");
		}
		return false;
	}
	
	public void backHomePage() {
		redirectToPage("HomePage.xhtml");
	}
	
	public void logoutIdle() {
		logoutUserApplication();
	}

	public SessionUtils getSessionUtils() {
		return sessionUtils;
	}

	public void setSessionUtils(SessionUtils sessionUtils) {
		this.sessionUtils = sessionUtils;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public ServiceUserUtils getServiceUserUtils() {
		return serviceUserUtils;
	}

	public void setServiceUserUtils(ServiceUserUtils serviceUserUtils) {
		this.serviceUserUtils = serviceUserUtils;
	}

	public UsersSearch getUsersSearch() {
		return usersSearch;
	}

	public void setUsersSearch(UsersSearch usersSearch) {
		this.usersSearch = usersSearch;
	}

	public DateUtils getDateUtils() {
		return dateUtils;
	}

	public void setDateUtils(DateUtils dateUtils) {
		this.dateUtils = dateUtils;
	}

	public MessagesUtils getMessagesUtils() {
		return messagesUtils;
	}

	public void setMessagesUtils(MessagesUtils messagesUtils) {
		this.messagesUtils = messagesUtils;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
	
}
