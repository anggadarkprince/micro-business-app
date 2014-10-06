/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sketchproject.mbapp.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Asus
 */
public class DerbyDatabase {
    
    /* the default framework is embedded*/
    private static String framework = "embedded";
    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String protocol = "jdbc:derby:";
    
    /* variable final to connection */
    private static final String USER = "root";
    private static final String PASSWORD = "toor";
    private static final String DB = "C:/Users/Asus/.netbeans-derby/Shop";
    
    /* list of Statements, PreparedStatements*/
    public static ArrayList statements = new ArrayList();
    public static PreparedStatement psSelect = null;
    public static PreparedStatement psInsert = null;
    public static PreparedStatement psUpdate = null;
    public static PreparedStatement psDelete = null;
    public static Statement s = null;
    public static ResultSet rs = null;
    public static ResultSetMetaData rsMetaData = null;
    
    /* connection variable */
    public static Connection connection = null;
    
    public static void derbyConnect() {
        Properties props = new Properties();
        props.put("user", USER);
        props.put("password", PASSWORD);            
        try {
            connection = DriverManager.getConnection(protocol + DB, props);
            connection.setAutoCommit(false);
            //System.out.println("Connected to database " + DB);   
        } catch (SQLException sqle) {
            printSQLException(sqle);
        }
    }
    
    public static void releaseDerbyResource(){
        System.out.println("\n----- Derby Release Resource -----");
        // ResultSet
        try {
            if (rs != null) {
                System.out.println("Result set released "+rs.toString());
                
                rs.close();
                rs = null;
            }
        } catch (SQLException sqle) {
            printSQLException(sqle);
        }

        // Statements and PreparedStatements
        int i = 0;
        while (!statements.isEmpty()) {
            // PreparedStatement extend Statement
            Statement st = (Statement) statements.remove(i);
            try {
                if (st != null) {
                    System.out.println("Statement released "+st.toString());
                    
                    st.close();
                    st = null;
                    
                }
            } catch (SQLException sqle) {
                printSQLException(sqle);
            }
        }

        //Connection
        try {
            if (connection != null) {
                connection.close();
                connection = null;
                
                System.out.println("Connection released");
            }
        } catch (SQLException sqle) {
            printSQLException(sqle);
        }
    }

    public static void shutdownDerby() {
        if (framework.equals("embedded")) {
            try {
                DriverManager.getConnection("jdbc:derby:;shutdown=true");
                //DriverManager.getConnection("jdbc:derby:" + dbName + ";shutdown=true");
            } catch (SQLException se) {
                if (((se.getErrorCode() == 50000) && ("XJ015".equals(se.getSQLState())))) {
                    System.out.println("Derby shut down normally");
                } else {
                    System.err.println("Derby did not shut down normally");
                    printSQLException(se);
                }
            }
        }
    }

    public static void printSQLException(SQLException e) {
        while (e != null) {
            System.err.println("\n----- SQLException -----");
            System.err.println("  SQL State:  " + e.getSQLState());
            System.err.println("  Error Code: " + e.getErrorCode());
            System.err.println("  Message:    " + e.getMessage());
            //e.printStackTrace(System.err);
            e = e.getNextException();
        }
    }

    private static void loadDerbyDriver() {
        try {
            Class.forName(driver).newInstance();
            System.out.println("Loaded the appropriate driver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("\nUnable to load the JDBC driver " + driver);
            System.err.println("Please check your CLASSPATH.");
            cnfe.printStackTrace(System.err);
        } catch (InstantiationException ie) {
            System.err.println("\nUnable to instantiate the JDBC driver " + driver);
            ie.printStackTrace(System.err);
        } catch (IllegalAccessException iae) {
            System.err.println("\nNot allowed to access the JDBC driver " + driver);
            iae.printStackTrace(System.err);
        }
    }

    private static void parseDerbyArguments(String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("derbyclient")) {
                framework = "derbyclient";
                driver = "org.apache.derby.jdbc.ClientDriver";
                protocol = "jdbc:derby://localhost:1527/";
            }
        }
    }
}
