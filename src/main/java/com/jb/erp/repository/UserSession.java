package com.jb.erp.repository;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.jb.erp.util.SessionUtils;

@SessionScoped
@Named
public class UserSession implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	SessionUtils sessionUtils;
	
	public Long getUserId() {
		
		HttpSession session = sessionUtils.getSession();
		
		if (session != null) {
			return (Long) session.getAttribute("userId");
		}else {
			return 0L;
		}
	}
}
