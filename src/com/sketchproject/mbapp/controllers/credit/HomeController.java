/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.controllers.credit;

import com.sketchproject.mbapp.models.AppModel;
import com.sketchproject.mbapp.models.pos.ReportModel;
import com.sketchproject.mbapp.utility.CurrencyModfier;
import com.sketchproject.mbapp.views.credit.CreditMenuScreen;
import com.sketchproject.mbapp.views.credit.HomeScreen;

/**
 *
 * @author Asus
 */
public class HomeController {
    CreditMenuScreen menu;
    HomeScreen home;
    
    AppModel mApp;
    
    public HomeController(CreditMenuScreen menu, HomeScreen home){
        this.menu = menu;
        this.home = home;
        
        mApp = new AppModel();
        
        update();
    }
    
    public void update(){
        home.setInfo(mApp.getSetting("APPPS"));
    }
    
}
