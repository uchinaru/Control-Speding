package com.jb.erp.util;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private static SimpleDateFormat sdfhora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static SimpleDateFormat sdfsimples = new SimpleDateFormat("dd/MM/yyyy");
	
	public Date dateTimeStampFormat() {
	       Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
	        String formattedDate = sdfhora.format(timeStamp);
	        try {
	            return sdfhora.parse(formattedDate);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	} 
	
	public Date transformaDataSimples(Date date) {
		String formattedDate = sdfsimples.format(date);
		try {
			return sdfsimples.parse(formattedDate);
		} catch (Exception e) {
			 return null;
		}
	}
	
}
