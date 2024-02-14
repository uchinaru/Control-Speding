package com.jb.erp.util;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateUtils implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public String dateTimeStampFormat() {
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		String data = sdf.format(timeStamp);
		return data;
	} 
}
