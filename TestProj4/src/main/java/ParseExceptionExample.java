//package com.javacodegeeks.core.ParseException;
 
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class ParseExceptionExample {
 
    public static void main(String[] args) throws ParseException {
        /*String dateStr = "2011-11-19";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         
            Date date;
             
            try {
                date = dateFormat.parse(dateStr);
                System.out.println(date);
 
            } catch (ParseException e) {
                e.printStackTrace();
            }*/
    
    	  SimpleDateFormat sdf =  new SimpleDateFormat("dd-MM-yyyy");
          
          Date d1= new Date(); //Get system date
         
          //Convert Date object to string
          String strDate = sdf.format(d1);
          System.out.println("Formated String is " + strDate);
         
          //Convert a String to Date
          d1  = sdf.parse("31-12-2009");
         
          System.out.println("Converted Date is : " +d1);

            Date d= new Date();
            System.out.println("Todays date is " +d);
    }
}