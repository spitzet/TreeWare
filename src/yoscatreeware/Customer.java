/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yoscatreeware;
import com.sun.deploy.util.StringUtils;

import java.sql.*;

/**
 *
 * @author MEAT WAGON
 */
public class Customer {
    private static String protocol = "jdbc:derby:";
    protected Connection con = null;
    protected Statement stmt;
    protected int ID;
    private short serviceSame;
    private String firstName;
    private String lastName;
    private String bStreetAddress;
    private String bCity;
    private String bState;
    private String bZip;
    private String phoneNumber;
    private String mobilePhoneNumber;
    
    
    protected String  sStreetAddress=null, sCity=null, sState=null, sZip=null;
    private String invoices;
    private String email = null;
    private String website = null;
    protected boolean sSame, isGarbage = false;
    static ResultSet rs;
    private String notes;
    
    public Customer(){
        setCustomerID();
    }
    public void Customer(String firstName, String lastName, String bStreetAddress,
            String bCity, String bState, String bZip, String phoneNumber, String mobilePhone,
            String sStreetAddress, String sCity, String sState, String sZip, String notes){
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.bStreetAddress = bStreetAddress;
        this.bCity = bCity;
        this.bState = bState;
        this.bZip = bZip;
        this.phoneNumber = phoneNumber;
        this.sStreetAddress = sStreetAddress;
        this.sCity = sCity;
        this.sState = sState;
        this.sZip = sZip;
        
        setCustomerID();
        
        //set customer id
    }
    
    public String getServiceStreetAddress(){
        return sStreetAddress;
    }
    
    public boolean isGarbage(){
        return isGarbage;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    public void setWebsite(String website){
        this.website = website;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setSAddress(String[] sAddress){
        sStreetAddress = sAddress[0] + "Ä";
        sCity = sAddress[1] + "Ä";
        sState = sAddress[2] + "Ä";
        sZip = sAddress[3] + "Ä";
        
        
                //modify to accept all the address elements
    }

    protected void addServiceAddress(String[] newSAddress){
            if(sStreetAddress == null){
                sStreetAddress = newSAddress[0] + "Ä";
                sCity = newSAddress[1] + "Ä";
                sState = newSAddress[2] + "Ä";
                sZip = newSAddress[3] + "Ä";
            }else{
                sStreetAddress += newSAddress[0] + "Ä";
                sCity +=  newSAddress[1] + "Ä";
                sState += newSAddress[2] + "Ä";
                sZip += newSAddress[3] + "Ä";
            }
            
      }
    
    protected void setVals(String firstName, String lastName, String bStreetAddress,
            String bCity, String bState, String bZip, String notes, String phoneNumber, String mobilePhone, String email, String website){
        this.firstName = firstName;
        this.lastName = lastName;
        this.bStreetAddress = bStreetAddress;
        this.bCity = bCity;
        this.bState = bState;
        this.bZip = bZip;
        this.notes = notes;
        this.phoneNumber = phoneNumber;
        this.mobilePhoneNumber = mobilePhone;
        this.email = email;
        this.website = website;
        
//        if(serviceSame ==1){
//            sSame = true;
//            this.serviceSame = 1;
//        }else{ 
//            sSame = false;
//            this.serviceSame =0;
//        }
    }
    
    private void setCustomerID(){
        connect();
        try{
            rs = stmt.executeQuery("SELECT * FROM CURRENT_ID");
            rs.absolute(1);
            ID = rs.getInt("ID");
            ID++;
            rs.deleteRow();
            rs.moveToInsertRow();
            rs.updateInt("ID", ID);
            rs.updateRow();
            con.commit();
            //con.close();
        }catch(SQLException sqle){
            System.out.println(sqle.getMessage());
            sqle.printStackTrace();
          
       
        }
        
        
    }
    private void connect(){
        try{
            
            String dbName ="yoscaDB";
            con = DriverManager.getConnection(protocol + "C:/Users/MEAT WAGON/.netbeans/7.1.1/derby/yoscaDB" );
            con.setAutoCommit(false);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE);
                    System.out.println("success");

        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
       
        
    }
    public void insertCustomer(){
        String dbName ="yoscaDB";
        PreparedStatement psInsert = null;
        //connect();
        try{
            rs = stmt.executeQuery("SELECT * from CUSTOMERS");
//            psInsert = con.prepareStatement("insert into CUSTOMERS values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
//            psInsert.setString(1, getFirstName());
//            psInsert.setString(2, getLastName());
//            psInsert.setString(3, getbStreetAddress());
//            psInsert.setString(4, getbCity());
//            psInsert.setString(5, getbState());
//            psInsert.setString(6, getbZip());
//            psInsert.setString(7, getPhoneNumber());
//            psInsert.setString(8, getWebsite());
//            psInsert.setString(9, sStreetAddress);
//            psInsert.setString(10, sCity);
//            psInsert.setString(11, sState);
//            psInsert.setString(12, sZip);
//            psInsert.setInt(13, ID);
//            psInsert.setString(14, getInvoices());
//            psInsert.setShort(15, getServiceSame());
//            //if(psInsert.execute()){
//                psInsert.execute();
            rs.first();
            System.out.println(rs.getString("FIRSTNAME"));
            
            rs.moveToInsertRow();
            rs.updateString("FIRSTNAME", firstName);
            rs.updateString("LASTNAME", lastName);
            rs.updateString("BSTREETADRESS",bStreetAddress);
            rs.updateString("BCITY",bCity);
            rs.updateString("BSTATE", bState);
            rs.updateString("BZIP", bZip);
            rs.updateString("PHONENUMBER", phoneNumber);
            rs.updateString("WEBSITE", website);
            rs.updateString("SSTREETADRESS", sStreetAddress);
            rs.updateString("SCITY", sCity);
            rs.updateString("SSTATE", sState);
            rs.updateString("SZIP", sZip);
            rs.updateInt("CUSTOMERID", ID);
            rs.updateString("INVOICES",invoices);
            rs.updateShort("BILLING_SERVICE_SAME",serviceSame);
                    
            rs.insertRow();
            stmt.close();
                System.out.println("added Sucessfully");
                isGarbage = true;
                con.commit();
                con.close();
                //psInsert.close();
           // }else{           
               // System.out.println("Customer not added successfully");
            //}
            
        }catch(SQLException sqle){
             System.out.println(sqle.getMessage());
                sqle.printStackTrace();
        }
    }

    /**
     * @return the serviceSame
     */
    public short getServiceSame() {
        return serviceSame;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the bStreetAddress
     */
    public String getbStreetAddress() {
        return bStreetAddress;
    }

    /**
     * @return the bCity
     */
    public String getbCity() {
        return bCity;
    }

    /**
     * @return the bState
     */
    public String getbState() {
        return bState;
    }

    /**
     * @return the bZip
     */
    public String getbZip() {
        return bZip;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return the invoices
     */
    public String getInvoices() {
        return invoices;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the web site
     *
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @return the sSame
     */
    public boolean issSame() {
        return sSame;
    }

}
