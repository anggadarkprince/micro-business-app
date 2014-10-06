/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.controllers.pos;

import com.sketchproject.mbapp.models.AppModel;
import com.sketchproject.mbapp.models.pos.ReportModel;
import com.sketchproject.mbapp.utility.CurrencyModfier;
import com.sketchproject.mbapp.views.pos.HomeScreen;
import com.sketchproject.mbapp.views.pos.PosMenuScreen;


/**
 *
 * @author Asus
 */
public class HomeController {
    PosMenuScreen menu;
    HomeScreen home;
    
    ReportModel mReport;
    AppModel mApp;
    
    public HomeController(PosMenuScreen menu, HomeScreen home){
        this.menu = menu;
        this.home = home;     
        
        mReport = new ReportModel();
        mApp = new AppModel();
        
        update();
    }
    
    public void update(){
        home.setTotalStock(ReportModel.getTotalStock());
        home.setTotalSold(ReportModel.getTotalSold());
        home.setTotalAssets(CurrencyModfier.format(ReportModel.getTotalAssets(),"IDR "));
        home.setItemAvg(CurrencyModfier.format(ReportModel.getItemRevenueAvg(),"IDR "));
        home.setMonthAvg(CurrencyModfier.format(ReportModel.getMonthRevenueAvg(),"IDR "));
        home.setMonthRevenue(CurrencyModfier.format(ReportModel.getItemRevenueAvg(),"IDR "));
        
        home.setInfo(mApp.getSetting("APPPS"));
    }
    
}
