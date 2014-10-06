/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class DateModifier {
    
    public static String formatDate(String pattern, Object date){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    
    public static String getYear(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYY");
        return sdf.format(date);
    }
    
    public static String getToday(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM YYY");
        return sdf.format(date);
    }
    
    public static String getToday(String pattern){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    
    public static String getTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
        return sdf.format(date);
    }
    
    public static String extractDatePart(String date, String part){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date parse = sdf.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(parse);
            switch (part) {
                case "Y":
                    return String.valueOf(c.get(Calendar.YEAR));
                case "M":
                    return String.valueOf(c.get(Calendar.MONTH));
                case "D":
                    return String.valueOf(c.get(Calendar.DATE));
            }
            
        } catch (ParseException ex) {
            System.out.println("Parse date error "+ex.getMessage());
        }
        return null;
    }
    
    public static String dateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(date);
        return s;
    }
    
    public static String dateToString(String pattern, Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(date);
        return s;
    }
    
    public static Date stringToDate(String date){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date s = sdf.parse(date);
            return s;
        } catch (ParseException ex) {
            System.out.println("Parse date error "+ex.getMessage());
        }
        return null;
    }
    
    public static Date stringToDate(String pattern, String date){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date s = sdf.parse(date);
            return s;
        } catch (ParseException ex) {
            System.out.println("Parse date error "+ex.getMessage());
        }
        return null;
    }
    
    public static int differenceBetweenDate(Date date1, Date date2)
    {
        return (int)( (date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24));
    }
    
    public static Date dateIncrementDecrement(Date startDate, int yVal, int mVal,int dVal)
    {
        try {
            Date date = startDate;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            
            calendar.add(Calendar.YEAR, yVal);
            calendar.add(Calendar.MONTH, mVal);
            calendar.add(Calendar.DATE, dVal);
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            return sdf.parse(sdf.format(calendar.getTime()));
        } catch (ParseException ex) {
            System.out.println("Parse date error "+ex.getMessage());
        }
        return null;
    }
        
}
