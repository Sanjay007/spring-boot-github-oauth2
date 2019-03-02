package com.example.springsocial.util;

import java.text.DateFormat;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

	public static String getCurrDateString(Date todaysDate){
	       

	       DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");

           String str3 = df3.format(todaysDate);
           return str3;

	}
	public static String toPrettyURL(String string) {
	    return Normalizer.normalize(string.toLowerCase(), Form.NFD)
	        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
	        .replaceAll("[^\\p{Alnum}]+", "-");
	}
}
