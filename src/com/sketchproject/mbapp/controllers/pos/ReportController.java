/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sketchproject.mbapp.controllers.pos;

import com.sketchproject.mbapp.models.pos.ReportModel;
import com.sketchproject.mbapp.utility.DateModifier;
import com.sketchproject.mbapp.views.NativeDialog;
import com.sketchproject.mbapp.views.pos.PosMenuScreen;
import com.sketchproject.mbapp.views.pos.ReportScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Asus
 */
public class ReportController {
    PosMenuScreen menu;
    ReportScreen report;
    ReportModel mReport;
    
    public ReportController(PosMenuScreen menu, ReportScreen report){
        this.menu = menu;
        this.report = report;
        
        mReport = new ReportModel();
        
        report.setReportData(mReport.getReportData("-1", "-1",  report.getFilter(), report.getCustomer(), report.getContact()));
        Object[] data = mReport.getRevenue("-1", "-1",  report.getFilter(), report.getCustomer(), report.getContact());
        System.out.println(data.length);
        if(data[0] != null){
            report.setTotalPurchase(data[0].toString());
            report.setTotalSelling(data[1].toString());
            report.setTotalRevenue(data[2].toString());
        }
        else{
            report.setTotalPurchase("0");
            report.setTotalSelling("0");
            report.setTotalRevenue("0");
        }
        report.setListenerGenerate(new HandlerGenerateReport());
    }
    
    private class HandlerGenerateReport implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(report.getDateFrom() == null || report.getDateTo() == null){
                menu.showOverlay();
                NativeDialog dialog = new NativeDialog(menu, "Report", "Silakan pilih tanggal dengan benar!", NativeDialog.DIALOG_INFORMATION);
                dialog.showDialog();
                menu.hideOverlay();
            }
            else{
                report.setReportData(mReport.getReportData(DateModifier.dateToString(report.getDateFrom()), DateModifier.dateToString(report.getDateTo()),  report.getFilter(), report.getCustomer(), report.getContact()));
            
                Object[] data = mReport.getRevenue(DateModifier.dateToString(report.getDateFrom()), DateModifier.dateToString(report.getDateTo()),  report.getFilter(), report.getCustomer(), report.getContact());
                if(data[0] != null){
                    report.setTotalPurchase(data[0].toString());
                    report.setTotalSelling(data[1].toString());
                    report.setTotalRevenue(data[2].toString());
                }
                else{
                    report.setTotalPurchase("0");
                    report.setTotalSelling("0");
                    report.setTotalRevenue("0");
                }
                
            }
            
        }
    }
}
