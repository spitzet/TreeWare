


package yoscaCalendar;

import javax.swing.*;
import java.awt.*;

/**
 * @author Mike Carlin
 *
 */
public class YoscaMainCal {
	
	public static void main(String args[]) throws Exception
	{
            
                YoscaMainCal jmc = new YoscaMainCal();
                JFrame frmMain = new JFrame("Yosca Calendar"); // Create frame
		frmMain.setSize(1100, 700); // Set size to 400x400 pixels
                frmMain.setResizable(true);
                frmMain.setVisible(true);
                frmMain.add(jmc.startCalendar());
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close when X
		      
        }
        
        public JTabbedPane startCalendar() throws Exception
        {
            YoscaMonthCal n = new YoscaMonthCal();
            YoscaDailyCal d = new YoscaDailyCal();
            JPanel jp = new JPanel();
            JTabbedPane jtp = new javax.swing.JTabbedPane();
            jtp.add("Monthly View", n.initCalendar());
            jtp.add("Daily View", d.initDailyCal());
            return jtp;
            
            
        }
        
        

}
