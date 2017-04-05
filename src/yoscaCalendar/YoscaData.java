
package yoscaCalendar;

/**
 *
 * @author Mike Carlin
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Driver;
import java.sql.*;
import java.util.Vector;


public class YoscaData{ 
    

    
    public Connection dbConnection()throws Exception
    {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        Class.forName(driver).newInstance();
        String myDB ="jdbc:derby://localhost:1527/YoscaDB";
        return DriverManager.getConnection(myDB,"Anthony","Yosca");
    }

    /**
    * This method will load vector of vector of string and load all the data in
    * the vector
    * @return vector of vector of string
    * @throws java.lang.Exception
    */
    public Vector getDaily(String date)throws Exception
    {
        Vector<Vector<String>> eventList = new Vector<>();
        //ListTableModel ltm = null;
        Connection conn = dbConnection();
        PreparedStatement pre = conn.prepareStatement("select * from APP.EVENTS where DATE='" + date + "'");

        ResultSet rs = pre.executeQuery();
        
        
        while(rs.next())
        {
            Vector<String> event = new Vector<>();
            event.add(rs.getString(1)); //Date
            event.add(rs.getString(2)); //Time
            event.add(rs.getString(3)); //Name
            event.add(rs.getString(4)); //Description
            eventList.add(event);
        }
        
        /*Close the connection after use (MUST)*/
        
        if(conn!=null)
        conn.close();

        return eventList;
    }
    
    public void addEvent(String date, String time, String name, String description) throws Exception
    {
        Connection conn = dbConnection();
        //String statement = "INSERT INTO APP.EVENTS VALUES (" + "'" date "','" +  time + "','" + name "','" + Description "')";
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO APP.EVENTS ( DATE, TIME, NAME, DESCRIPTION ) " +
                                                        " VALUES (?, ?, ?, ?)");
        pstmt.setString( 1, date );
        pstmt.setString( 2, time ); 
        pstmt.setString( 3, name ); 
        pstmt.setString( 4, description );
        
        pstmt.executeUpdate();
    }
}
