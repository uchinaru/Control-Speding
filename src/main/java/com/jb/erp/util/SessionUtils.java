package com.jb.erp.util;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;


public class SessionUtils implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private HttpSession session;

	public HttpSession createSession() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		this.session = (HttpSession) externalContext.getSession(true);
		return session;
	}

	public HttpSession getSession() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		this.session = (HttpSession) externalContext.getSession(false);
		return session;
	}
	
	public void sessionClose() {
		session.invalidate();
	}
}
