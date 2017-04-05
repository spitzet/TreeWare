package yoscaCalendar; /**
 * 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import yoscaCalendar.YoscaMonthCal.cmbYear_Action;
import yoscaCalendar.YoscaMonthCal.tblCalendarRenderer;

/**
 * @author Mike Carlin
 *
 */




@SuppressWarnings("serial")
public class YoscaWeeklyCal extends JPanel 
{
        static JLabel lblMonth, lblYear;
	static JButton btnPrev, btnNext;
	static JTable tblCalendar;
	static JComboBox cmbYear;
	static DefaultTableModel mtblCalendar; // Table model
	static JScrollPane stblCalendar; // The scrollpane
	static JPanel pnlCalendar; 
	static int realYear, realMonth, realDay, currentYear, currentMonth, currentWeek;
        
    public JPanel initWeeklyCal()
    {
        
        
        try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException e) {
				} catch (InstantiationException e) {
				} catch (IllegalAccessException e) {
				} catch (UnsupportedLookAndFeelException e) {
                                }
																		// is clicked

				// Create controls
				lblMonth = new JLabel("January");
				lblYear = new JLabel("Change Week:");
				cmbYear = new JComboBox();
				btnPrev = new JButton("<<");
				btnNext = new JButton(">>");
				mtblCalendar = new DefaultTableModel() {
					@Override
					public boolean isCellEditable(int rowIndex, int mColIndex) {
						return false;
					}
				};
				tblCalendar = new JTable(mtblCalendar);
				stblCalendar = new JScrollPane(tblCalendar);
				pnlCalendar = new JPanel(null);

				// Set border
				pnlCalendar.setBorder(BorderFactory.createTitledBorder("Calendar"));

				// Register action listeners
				//btnPrev.addActionListener(new btnPrev_Action());
				//btnNext.addActionListener(new btnNext_Action());
				cmbYear.addActionListener(new cmbYear_Action());

				// Add controls to pane
				//pane.add(pnlCalendar);
				pnlCalendar.add(lblMonth);
				pnlCalendar.add(lblYear);
				pnlCalendar.add(cmbYear);
				pnlCalendar.add(btnPrev);
				pnlCalendar.add(btnNext);
				pnlCalendar.add(stblCalendar);

				// Set bounds
				pnlCalendar.setBounds(0, 0, 1093, 650);
				lblMonth.setBounds(525 - lblMonth.getPreferredSize().width / 2, 25, 25,
						25);
				lblYear.setBounds(900, 610, 80, 20);
				cmbYear.setBounds(980, 610, 80, 20);
				btnPrev.setBounds(10, 25, 50, 25);
				btnNext.setBounds(1030, 25, 50, 25);
				stblCalendar.setBounds(10, 50, 1070, 550);


				// Get real month/year
				GregorianCalendar cal = new GregorianCalendar(); // Create calendar
				realDay = cal.get(Calendar.DAY_OF_MONTH); // Get day
				realMonth = cal.get(Calendar.MONTH); // Get month
				realYear = cal.get(Calendar.YEAR); // Get year
				currentMonth = realMonth; // Match month and year
				currentYear = realYear;
				
				// Add headers
				String[] headers = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" }; // All
																						// headers
				for (int i = 0; i < 7; i++) {
					mtblCalendar.addColumn(headers[i]);
				}

				tblCalendar.getParent().setBackground(tblCalendar.getBackground()); // Set
																					// background

				// No resize/reorder
				tblCalendar.getTableHeader().setResizingAllowed(false);
				tblCalendar.getTableHeader().setReorderingAllowed(false);

				// Single cell selection
				tblCalendar.setColumnSelectionAllowed(true);
				tblCalendar.setRowSelectionAllowed(true);
				tblCalendar
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				// Set row/column count
				tblCalendar.setRowHeight(525);
				mtblCalendar.setColumnCount(7);
				mtblCalendar.setRowCount(1);

				// Populate table
				for (int i = realYear - 100; i <= realYear + 100; i++) {
					cmbYear.addItem(String.valueOf(i));
				}
                                refreshWeekly(realMonth, realYear);
				return pnlCalendar;
    }
    public static void refreshWeekly(int month, int year)
        {
            String[] months = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };
		int nod, som; // Number Of Days, Start Of Month

		// Allow/disallow buttons
		//btnPrev.setEnabled(true);
		//btnNext.setEnabled(true);
		//if (month == 0 && year <= realYear - 10) {
		//	btnPrev.setEnabled(false);
		//} // Too early
		if (month == 11 && year >= realYear + 100) {
			btnNext.setEnabled(false);
		} // Too late
		lblMonth.setText(months[month]); // Refresh the month label (at the top)
		lblMonth.setBounds(525 - lblMonth.getPreferredSize().width / 2, 25,
				180, 25); // Re-align label with calendar
		cmbYear.setSelectedItem(String.valueOf(year)); // Select the correct
														// year in the combo box

		// Clear table
		for (int i = 0; i < 6; i++) 
                {
                    mtblCalendar.setValueAt(null, 0, i);
		}

		// Get first day of month and number of days
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		som = cal.get(Calendar.DAY_OF_WEEK);
                currentWeek = 1;
                
                int daysInWeek[]={0,0,0,0,0,0,0};
                
                for(int i= som; i <7; i++)
                {
                    daysInWeek[i] = (i + som) % 7;
                }
		// Draw calendar
		for (int i = 0; i < 7; i++) 
                {
			mtblCalendar.setValueAt(daysInWeek[i], 0, i);
		}

		// Apply renderer
		tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0),
				new tblCalendarRenderer());
        }
    static class cmbYear_Action implements ActionListener {
		/**
		 * TODO Javadoc.
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (cmbYear.getSelectedItem() != null) {
				String b = cmbYear.getSelectedItem().toString();
				currentYear = Integer.parseInt(b);
				refreshWeekly(currentMonth, currentYear);
			}
		}
	}
    
}