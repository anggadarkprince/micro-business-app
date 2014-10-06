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
public class SystemInfo {
    private static String OS = System.getProperty("os.name").toLowerCase();
    private static String version = System.getProperty("os.version").toLowerCase();
    private static String architecture = System.getProperty("os.arch").toLowerCase();
    private static String user = System.getProperty("user.name").toLowerCase();
    
    private static String vendor = System.getProperty("java.vendor");
    private static String vendorver = System.getProperty("java.version");
    private static String vm = System.getProperty("java.vm.name");
    private static String specification = System.getProperty("java.vm.specification.name");
    
    public static boolean isWindows() {
        return (OS.contains("win"));
    }

    public static boolean isMac() {
        return (OS.contains("mac"));
    }

    public static boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux") || OS.indexOf("aix") > 0 );
    }

    public static boolean isSolaris() {
        return (OS.contains("sunos"));
    }
    
    public static String getOS(){
        return OS;
    }
    
    public static String getVersion(){
        return version;
    }
    
    public static String getArchitecture(){
        return architecture;
    }
    
    public static String getUser(){
        return user;
    }
    
    public static String getVendor(){
        return vendor;
    }
    
    public static String getVendorVersion(){
        return vendorver;
    }
    
    public static String getVm(){
        return vm;
    }
    
    public static String getSpecification(){
        return specification;
    }
}
