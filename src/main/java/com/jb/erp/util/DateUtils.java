package com.jb.erp.util;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private static SimpleDateFormat sdfComHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static SimpleDateFormat sdfsimples = new SimpleDateFormat("dd/MM/yyyy");
	
	public Date dateTimeStampFormat() {
	       Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
	        String formattedDate = sdfComHora.format(timeStamp);
	        try {
	            return sdfComHora.parse(formattedDate);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	} 

	public String transformaDataSimplesString(Date date) {
		try {
			return sdfsimples.format(date);
		} catch (Exception e) {
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

	public Date transformaDataSimples(String date) {
		String formattedDate = sdfsimples.format(date);
		try {
			return sdfsimples.parse(formattedDate);
		} catch (Exception e) {
			 return null;
		}
	}
	
	public Date transformaDataCompleta(Date date) {
		String formattedDate = sdfComHora.format(date);
		try {
			return sdfComHora.parse(formattedDate);
		} catch (Exception e) {
			 return null;
		}
	}
	
	public int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		return c.get(Calendar.MONTH);
		
	}
	
	public String getMes(Date date) {
		String result = "";
		
		String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
		
		for (int i = 0; i < meses.length; i++) {
			if (i == getMonth(date))
				result = meses[i];
		}
		
		return result;
	}
	
}
