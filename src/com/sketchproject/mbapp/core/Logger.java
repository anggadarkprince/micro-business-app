/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.core;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Logger {
    
    private String _path;
    private String _module;
    private String _value;
    private Date _date;
    
    public Logger(){
        _module = "System";
        _value = "Init Program at "+_date.toString();
    }
    
    public Logger(String module, String value){
        _module = module;
        _value = value;
    }
    
    public void setModule(String module){
        _module = module;
    }
    public void setValue(String value){
        _value = value;
    }
        
    public String getModule(){
        return _module;
    }
    public String getValue(){
        return _value;
    }
    
    public boolean writeLog(){
        return true;
    }
}
