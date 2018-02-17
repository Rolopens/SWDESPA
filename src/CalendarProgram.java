/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Arturo III
 */

import dayobjects.Events;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CalendarProgram{
	
        /**** Day Components ****/
	public int yearBound, monthBound, dayBound, yearToday, monthToday;

        /**** Swing Components ****/
        public JLabel monthLabel, yearLabel;
	public JButton btnPrev, btnNext;
        public JComboBox cmbYear;
	public JFrame frmMain;
	public Container pane;
	public JScrollPane scrollCalendarTable;
	public JPanel calendarPanel;
        
        /**** Calendar Table Components ***/
	public JTable calendarTable;
        public DefaultTableModel modelCalendarTable;
        
        //List of events
        private ArrayList<Events> events = new ArrayList<Events>();
        //For observers
        private ArrayList<ObserverFB> observers = new ArrayList<ObserverFB>();
        //More Variables
        private CalendarProgram thisProgram;
        private CSVParser CSVp;
        private PSVParser PSVp;
        private boolean flag;
        
        public void trueFlag(){
            this.flag = true;
        }
        
        public void attachObserver(ObserverFB o){
            observers.add(o);
        }
        
        public void notifyObservers(){
            int j;
            for (j=0;j<observers.size();j++)
                observers.get(j).update();
        }
        
        public void addEventFromParser(Events e){
            events.add(e);
        }

        public ArrayList<Events> getEvents() {
            return events;
        }
        
        public JTable getCalendarTable() {
            return calendarTable;
        }
        
        /* public int getYearBound() {
            return yearBound;
        }
        
        public int getMonthBound() {
            return monthBound;
        } */
        
        public int getYearToday() {
            return yearToday;
        }
        
        public int getMonthToday() {
            return monthToday;
        }
        
        public void readAndStoreEvents(){
            CSVp.readData();
            PSVp.readData();
            CSVp.processData();
            PSVp.processData();
            
        }
        
        public void storeData(){
            CSVp.writeData();
            PSVp.writeData();
        }
        
        public void refreshCalendar(int month, int year)
        {
		String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		int nod, som, i, j;
			
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		if (month == 0 && year <= yearBound-10)
                    btnPrev.setEnabled(false);
		if (month == 11 && year >= yearBound+100)
                    btnNext.setEnabled(false);
                
		monthLabel.setText(months[month]);
		monthLabel.setBounds(320-monthLabel.getPreferredSize().width/2, 50, 360, 50);
                
		cmbYear.setSelectedItem(""+year);
		
		for (i = 0; i < 6; i++)
			for (j = 0; j < 7; j++)
				modelCalendarTable.setValueAt(null, i, j);
		
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);
                
                //System.out.println(nod + "/"+ som);
		
                
                //vital loop
		for (i = 1; i <= nod; i++)
                {
			int row = new Integer((i+som-2)/7);
			int column  =  (i+som-2)%7;
                        //System.out.println(row + " " + column);
			modelCalendarTable.setValueAt(i, row, column);
                        
                        for(int k = 0; k < events.size(); k++) {
                            if((events.get(k).getMonth()-1 == month) && (events.get(k).getYear() == year) && (events.get(k).getDate() == i))
                                    modelCalendarTable.setValueAt(modelCalendarTable.getValueAt(row, column) + " " + events.get(k).getEventName(), row, column);
                            else if((events.get(k).isIsHoliday()) && (events.get(k).getMonth()-1 == month) && (events.get(k).getYear() <= year) && (events.get(k).getDate() == i))
                                    modelCalendarTable.setValueAt(modelCalendarTable.getValueAt(row, column) + " " + events.get(k).getEventName(), row, column);
                        }
                             
                        
		}
                storeData();
		calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new TableRenderer(events));
                
                
	}
        
	public CalendarProgram()
        {
		try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
		catch (Exception e) {}
              
                //Added Code
                thisProgram = this;
                CSVp = new CSVParser(this);
                PSVp = new PSVParser(this);
                flag = true;
                readAndStoreEvents();
                
		frmMain = new JFrame ("Calendar Application");
                frmMain.setSize(660, 750);
		pane = frmMain.getContentPane();
		pane.setLayout(null);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		monthLabel = new JLabel ("January");
		yearLabel = new JLabel ("Change year:");
		cmbYear = new JComboBox();
		btnPrev = new JButton ("<<");
		btnNext = new JButton (">>");
		modelCalendarTable = new DefaultTableModel()
                {
                    public boolean isCellEditable(int rowIndex, int mColIndex)
                    {
                        return false;
                    }
                };
                
		calendarTable = new JTable(modelCalendarTable);
                calendarTable.addMouseListener(new MouseAdapter()   
                {  
                    public void mouseClicked(MouseEvent evt)  
                    {  
                        if(flag == true){
                        int col = calendarTable.getSelectedColumn();  
                        int row = calendarTable.getSelectedRow();
                        addEvent temp;
                        if(calendarTable.getValueAt(row, col) != null)                                                        
                            temp = new addEvent(thisProgram);
                        storeData();
                        flag = false;
                        }
                        //System.out.println(monthToday+1);
                        //System.out.println(yearToday);
                    }
                });
                
                
		scrollCalendarTable = new JScrollPane(calendarTable);
		calendarPanel = new JPanel(null);

		calendarPanel.setBorder(BorderFactory.createTitledBorder("Calendar"));
		
		btnPrev.addActionListener(new btnPrev_Action());
		btnNext.addActionListener(new btnNext_Action());
		cmbYear.addActionListener(new cmbYear_Action());
		
		pane.add(calendarPanel);
		calendarPanel.add(monthLabel);
		calendarPanel.add(yearLabel);
		calendarPanel.add(cmbYear);
		calendarPanel.add(btnPrev);
		calendarPanel.add(btnNext);
		calendarPanel.add(scrollCalendarTable);
		
                calendarPanel.setBounds(0, 0, 640, 670);
                monthLabel.setBounds(320-monthLabel.getPreferredSize().width/2, 50, 200, 50);
		yearLabel.setBounds(20, 610, 160, 40);
		cmbYear.setBounds(460, 610, 160, 40);
		btnPrev.setBounds(20, 50, 100, 50);
		btnNext.setBounds(520, 50, 100, 50);
		scrollCalendarTable.setBounds(20, 100, 600, 500);
                
		frmMain.setResizable(false);
		frmMain.setVisible(true);
		
		GregorianCalendar cal = new GregorianCalendar();
		dayBound = cal.get(GregorianCalendar.DAY_OF_MONTH);
		monthBound = cal.get(GregorianCalendar.MONTH);
		yearBound = cal.get(GregorianCalendar.YEAR);
		monthToday = monthBound; 
		yearToday = yearBound;
		
		String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
		for (int i=0; i<7; i++){
			modelCalendarTable.addColumn(headers[i]); 
		}
		
		calendarTable.getParent().setBackground(calendarTable.getBackground()); //Set background
                
		calendarTable.getTableHeader().setResizingAllowed(false);
		calendarTable.getTableHeader().setReorderingAllowed(false);

		calendarTable.setColumnSelectionAllowed(true);
		calendarTable.setRowSelectionAllowed(true);
		calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		calendarTable.setRowHeight(76);
		modelCalendarTable.setColumnCount(7);
		modelCalendarTable.setRowCount(6);
		
		for (int i = yearBound-100; i <= yearBound+100; i++)
                {
			cmbYear.addItem(String.valueOf(i));
		}
		
                
		refreshCalendar (monthBound, yearBound); //Refresh calendar
	}
	

	class btnPrev_Action implements ActionListener
        {
		public void actionPerformed (ActionEvent e)
                {
			if (monthToday == 0)
                        {
				monthToday = 11;
				yearToday -= 1;
			}
			else
                        {
				monthToday -= 1;
			}
			refreshCalendar(monthToday, yearToday);
		}
	}
	class btnNext_Action implements ActionListener
        {
		public void actionPerformed (ActionEvent e)
                {
			if (monthToday == 11)
                        {
				monthToday = 0;
				yearToday += 1;
			}
			else
                        {
				monthToday += 1;
			}
			refreshCalendar(monthToday, yearToday);
		}
	}
	class cmbYear_Action implements ActionListener
        {
		public void actionPerformed (ActionEvent e)
                {
			if (cmbYear.getSelectedItem() != null)
                        {
				String b = cmbYear.getSelectedItem().toString();
				yearToday = Integer.parseInt(b);
				refreshCalendar(monthToday, yearToday);
			}
		}
	}
}
