/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.utility;

/**
 *
 * @author Asus
 */
public class CurrencyModfier {
    
    public static String format(Object value, String prefix){        
        int reformat = Integer.parseInt(String.valueOf(value));
        String val = String.valueOf(reformat);
        
        String newformat = val;
        
        if(val.length() > 3){
            newformat = reformat/1000+" Ribu";
        }
        if(val.length() > 6){
            newformat = reformat/1000000+" Juta";
        }
        if(val.length() > 9){ 
            newformat = reformat/1000000000+" Milyar";
        }
        
        return prefix+""+newformat;
    }
}
