package com.example.project.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Utils {
	
	private static SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
	
	public static String generateIP(){
		
		StringBuffer buff = new StringBuffer();
		
		Random rand = new Random();
		
		int i = 0;
		
		while(i<6){
			int myRandomNumber = rand.nextInt(0xFFFF); // Generates a random number between 0x0000 and 0xFFFF
			System.out.printf("%x\n",myRandomNumber); 

			String result = Integer.toHexString(myRandomNumber); // Random hex number in result
			
			if(i==5)
			 buff.append(result);
			else
			 buff.append(result+".");
			
			i++;
		}
		
		return buff.toString();
	}
	
	public static String generaStrFecha(Date fecha){
		
		return formato.format(fecha);
		
	}
	public static String generaStrFechaHoy(){
		
		return formato.format(new Date());
		
	}
	public static Date generaFechaFormato(String strfecha) throws ParseException{
		return formato.parse(strfecha);
	}
	
	public static int comparaFechas(Date fecha, String strfecha) throws ParseException{
		
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(formato.parse(strfecha));
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(fecha);
			
		if(cal1.after(cal2))
			return 1;
		else
			return -1;	
		
	}
	
    public static int modificadoHoy(Date fecha, String strfecha) throws ParseException{
		
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(formato.parse(strfecha));
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(fecha);
		
		if(cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH) &&
				cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
				cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)){
			return 0;
		}else{
			return 1;	
		}
		
	}
    
    public static String formateaIP(String entrada){
    	return entrada.replaceAll(":", ".");
    }

}
