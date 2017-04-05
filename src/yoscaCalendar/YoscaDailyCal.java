package yoscaCalendar; /**
 * 
 */

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;
import org.jdesktop.swingx.*;
import java.util.Collections;
import javax.swing.table.DefaultTableModel;

/**
 * @author Mike Carlin
 *
 */
public class YoscaDailyCal {
    
    private Vector<Vector<String>> data; //used for data from database
   
    private Vector<String> headers;
    
    YoscaData dbengine = new YoscaData();
    
    JXDatePicker jdp = new JXDatePicker();
    JTable jt = new JTable();
    
    public JPanel initDailyCal() throws Exception
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String stringDate = sdf.format(date);
        
        headers = new Vector<>();
        headers.add("Date");
        headers.add("Time");
        headers.add("Client");
        headers.add("Description");
        JPanel jtp = new JPanel();
        JPanel container = new JPanel();
        data = dbengine.getDaily(stringDate);
        JScrollPane jsp = new JScrollPane();
        JButton addButton = new JButton("Add Event");
        //JXDatePicker jdp = new JXDatePicker();
       // JTable jt = new JTable();
        JLabel pickDate = new JLabel("Pick Date :");
        jt.setModel(new DefaultTableModel(
            data,headers
        ));
        jsp.add(jt);
        jsp.setViewportView(jt);
        jtp.add(jsp);
        jtp.add(jdp);
        jtp.setBounds(0, 0, 1093, 650);
	jdp.setBounds(1030, 25, 50, 25);
	jsp.setBounds(10, 50, 1070, 550);
        
        jdp.addActionListener(new java.awt.event.ActionListener()  {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try
               {
                jdpActionPerformed(evt);
               }catch(Exception e){
                 e.printStackTrace();
                }
        
            }
        });
       
        addButton.addActionListener(new java.awt.event.ActionListener() {
            
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        
        //jtp.add(jsp);
        //jtp.add(jdp);
        
        javax.swing.GroupLayout jtpLayout = new javax.swing.GroupLayout(jtp);
        jtp.setLayout(jtpLayout);
        jtpLayout.setHorizontalGroup(
            jtpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jtpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jtpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jtpLayout.createSequentialGroup()
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jdp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jtpLayout.createSequentialGroup()
                        .addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jtpLayout.setVerticalGroup(
            jtpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jtpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jtpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jdp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton))
                .addGap(18, 18, 18)
                .addComponent(jsp, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(container);
        container.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        
        
        
        return container;
    }
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {
        AddEvent njf = new AddEvent();
        njf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new AddEvent().setVisible(true);
            }
        });
        
    }
    
    private void jdpActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        date = jdp.getDate();
        String stringDate = sdf.format(date);
        System.out.print(stringDate);
        data = dbengine.getDaily(stringDate);
        jt.setModel(new DefaultTableModel(
            data,headers
        ));
        Collections.sort(data, new ColumnSorter(1));
        jt.setModel(new DefaultTableModel(
            data,headers
        ));
    }
}
