package com.example.springsocial.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

	public static String getCurrDateString(){
	       Date todaysDate = new Date();

	       DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");

           String str3 = df3.format(todaysDate);
           return str3;

	}
}
