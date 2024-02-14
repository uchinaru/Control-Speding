package com.jb.erp.util;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public Date dateTimeStampFormat() {
	       Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
	        String formattedDate = sdf.format(timeStamp);
	        try {
	            return sdf.parse(formattedDate);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	} 
}
