package yoscatreeware;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Properties;
import javax.swing.*;

/**
 *
 * @author MEAT WAGON
 */
public class YoscaTreeWare extends JFrame{
    
    private static Connection con = null;        

    private static String framework = "embedded";
    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String protocol = "jdbc:derby:";
    static Statement stmt;
    static ResultSet rs;
    static Customer currCustomer = null;

    /**
     * @param args the command line arguments
     */
    public YoscaTreeWare(){
        loadDriver();
        //connect
    }
    protected void addServiceAddress(String[] newSAddress){
        try{
            for(int i = 0; i<4; i++){    
                errorCheck(newSAddress[i]);
            }

            if(currCustomer == null || currCustomer.isGarbage){
                currCustomer = new Customer();
            }
            
            currCustomer.addServiceAddress(newSAddress);
            
        }catch(IncorrectEntryException err){
            System.out.println(err.getMessage());
        
        }
     }
    protected Customer getCurrCustomer(){
        return currCustomer;
    }
    protected void setCurrCustomer(Customer currCustomer){
        this.currCustomer = currCustomer;
    }
    
    private static void loadDriver() {
        /*
         *  The JDBC driver is loaded by loading its class.
         *  If you are using JDBC 4.0 (Java SE 6) or newer, JDBC drivers may
         *  be automatically loaded, making this code optional.
         *
         *  In an embedded environment, this will also start up the Derby
         *  engine (though not any databases), since it is not already
         *  running. In a client environment, the Derby engine is being run
         *  by the network server framework.
         *
         *  In an embedded environment, any static Derby system properties
         *  must be set before loading the driver to take effect.
         */
        try {
            Class.forName(driver).newInstance();
            System.out.println("Loaded the appropriate driver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("\nUnable to load the JDBC driver " + driver);
            System.err.println("Please check your CLASSPATH.");
            cnfe.printStackTrace(System.err);
        } catch (InstantiationException ie) {
            System.err.println(
                        "\nUnable to instantiate the JDBC driver " + driver);
            ie.printStackTrace(System.err);
        } catch (IllegalAccessException iae) {
            System.err.println(
                        "\nNot allowed to access the JDBC driver " + driver);
            iae.printStackTrace(System.err);
        }
    }
    
    protected String box(String b){
        return null;
    }
    protected void errorCheck(String s) throws IncorrectEntryException{
        if(s == null || s.equals("")){
            throw new IncorrectEntryException("Entries cannot be Null",1);
        }
        
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == 'Ä')
                throw new IncorrectEntryException("Dis Allowed character detected",2);
        }
  
    }
    
    public static void connect(){
        ArrayList statements = new ArrayList();
        try{
            
            String dbName ="yoscaDB";
            con = DriverManager.getConnection(protocol + "C:/Users/MEAT WAGON/.netbeans/7.1.1/derby/yoscaDB" );
            con.setAutoCommit(false);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE);
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
       
        System.out.println("success");
        
        String SQL = "SELECT * FROM CUSTOMERS";
        
        
        
        
       // try{
            //stmt.execute("insert into CUSTOMERS values('mike', 'rotch', '22261 rambling oaks dr.', 'grass valley', 'ca', '95949','5305598709', null, null, null, null, null, 95673, null, 1)");
          
           // rs = stmt.executeQuery(SQL);
            //rs.first();
//            System.out.println(rs.getString("FIRSTNAME"));
//            System.out.println(rs.getString("LASTNAME"));
//            System.out.println(rs.getString("BSTREETADRESS"));
//            rs.next();
//            System.out.println(rs.getString("FIRSTNAME"));
//       con.commit();
////         
////        rs = stmt.executeQuery(SQL);
////        rs.first();
////        System.out.print(rs.getString("FIRSTNAME"));
//        }catch(SQLException err){
//            System.out.println(err.getMessage());
//            System.exit(1);
//            
//        }
        
    }
  
}
