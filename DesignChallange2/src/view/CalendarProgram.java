package view;
/**
 *
 * @author Arturo III; Llamas, Antonio Miguel B.; Pena, Romeo Manuel N.
 */
import controller.CalendarController;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import model.EventsObject;

public class CalendarProgram {
    /**
     * ** Day components ***
     */
    public int yearBound, monthBound, dayBound, yearToday, monthToday;

    /**
     * ** Swing components ***
     */
    public JLabel monthLabel, yearLabel;
    public JButton btnPrev, btnNext;
    public JComboBox cmbYear;
    public JFrame frmMain;
    public Container pane;
    public JScrollPane scrollCalendarTable;
    public JPanel calendarPanel;
    
    public JComboBox startTime;
    public JComboBox endTime;
    public JButton addEvent;
    public JTextArea dateArea, eventName;
    public JLabel start, end;
    public ButtonGroup bg;
    public JRadioButton eventButton;
    public JRadioButton taskButton;

    /**
     * ** Calendar table components **
     */
    public JTable calendarTable;
    public DefaultTableModel modelCalendarTable;
    
    /**
     * ** Activity table components **
     */
    public JScrollPane scrollActivityTable;
    public JTable activityTable;
    public DefaultTableModel modelActivityTable;
    public JFrame activityFrame;
    public JPanel activityPanel;
    
    /**
     * ** Agenda table components **
     */
    public JScrollPane scrollAgendaTable;
    public JTable agendaTable;
    public DefaultTableModel modelAgendaTable;
    public JFrame agendaFrame;
    public JPanel agendaPanel;
    
    public JPanel buttonsPanel;
    public JButton removeEvent;
    public JButton markDone;
    
    public JTabbedPane tabbedPane;

    // List of events
    private ArrayList<EventsObject> events = new ArrayList<EventsObject>();
    private CalendarController controller;

    public CalendarController getController() {
        return controller;
    }

    public void setController(CalendarController controller) {
        this.controller = controller;
    }

    public void setEventsArrayList(ArrayList<EventsObject> events) {
        this.events = events;
    }

    public ArrayList<EventsObject> getEvents() {
        return events;
    }

    public JTable getCalendarTable() {
        return calendarTable;
    }

    public int getYearToday() {
        return yearToday;
    }

    public int getMonthToday() {
        return monthToday;
    }
    
    public CalendarProgram() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        
        frmMain = new JFrame("Calendar Application");
        frmMain.setSize(660, 750);
        frmMain.setSize(1300, 780);
        pane = frmMain.getContentPane();
        pane.setLayout(null);
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        monthLabel = new JLabel("January");
        yearLabel = new JLabel("Change year:");
        cmbYear = new JComboBox();
                
        String[] timeslots = new String[]{"0:00", "0:30", "1:00", "1:30", "2:00", "2:30", "3:00", "3:30", "4:00", "4:30", 
                                            "5:00", "5:30", "6:00","6:30", "7:00", "7:30", "8:00", "8:30", "9:00", "9:30", 
                                            "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
                                            "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00",
                                            "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"};
        
        
        
        String[] activityHeaders = {"Time", "Activity", "Select"};
        modelActivityTable = new DefaultTableModel() {
            @Override
            public String getColumnName(int index) {
                return activityHeaders[index];
            }
        };
        
        modelActivityTable.setColumnCount(2);
        modelActivityTable.setRowCount(48);
        
        for(int i = 0; i < 48; i++) {
            modelActivityTable.setValueAt(timeslots[i], i, 0);
        }
        
        activityTable = new JTable(modelActivityTable) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JComponent component = (JComponent) super.prepareRenderer(renderer, row, column);
                if(getValueAt(row, 0).toString().contains(":30") && column == 0)
                    component.setForeground(Color.WHITE);
                else
                    component.setForeground(Color.BLACK);
                return component;
            }
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        // activityTable.setBounds(660, 20, 600, 650);
        activityPanel = new JPanel();
        // activityPanel.setBounds(660, 20, 600, 650);
        activityPanel.setVisible(true);
        // activityFrame.add(activityTable);
        scrollActivityTable = new JScrollPane(activityTable);
        activityPanel.add(scrollActivityTable);
        // pane.add(activityPanel);
        // scrollActivityTable = new JScrollPane(activityTable);
        
        modelAgendaTable = new DefaultTableModel(activityHeaders, 0) {
            @Override
            public Class getColumnClass(int column) {
              return (getValueAt(0, column).getClass());
            }
        };
        agendaTable = new JTable(modelAgendaTable) {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return (mColIndex == 2);
            }
        };
        
        agendaPanel = new JPanel();
        agendaPanel.setVisible(true);
        scrollAgendaTable = new JScrollPane(agendaTable);
        agendaPanel.add(scrollAgendaTable);
          
        removeEvent = new JButton("Remove");
        markDone = new JButton("Mark done");
        removeEvent.addActionListener(new btn_removeEvent());
        markDone.addActionListener(new btn_markDone());
        buttonsPanel = new JPanel();
        buttonsPanel.setVisible(true);      
        buttonsPanel.add(removeEvent);
        buttonsPanel.add(markDone);
        agendaPanel.add(buttonsPanel);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(660, 20, 600, 650);
        tabbedPane.addTab("Day", activityPanel);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        
        tabbedPane.addTab("Agenda", agendaPanel);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
        pane.add(tabbedPane);
        
        startTime = new JComboBox();
        endTime = new JComboBox();
        endTime.setModel(new DefaultComboBoxModel(timeslots));
        startTime.setModel(new DefaultComboBoxModel(timeslots));
        start = new JLabel("Start time");
        end = new JLabel("End time");
        eventButton = new JRadioButton("Event");
        taskButton = new JRadioButton("Task");
        dateArea = new JTextArea();
        eventName = new JTextArea("Enter name of event/task");
        addEvent = new JButton("Add");
        
        btnPrev = new JButton("<<");
        btnNext = new JButton(">>");
        
        dateArea.setEditable(false);
          
        modelCalendarTable = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return true;
            }
        };
        modelCalendarTable = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };

        calendarTable = new JTable(modelCalendarTable);
        calendarTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                try {
                    int col = calendarTable.getSelectedColumn();  
                    int row = calendarTable.getSelectedRow();
                    dateArea.setText(yearToday + "-" + (monthToday+1) +"-"+ modelCalendarTable.getValueAt(row, col).toString().split(" ")[0]);
                    
                    modelAgendaTable.setNumRows(0);
                    
                    for (int k = 0; k < 44; k++)
                        modelActivityTable.setValueAt(null, k, 1);
                    
                    for (int k = 0; k < events.size(); k++) {
                        if (((Integer.parseInt(events.get(k).getDate().split("-")[1]) - 1 == monthToday) && (Integer.parseInt(events.get(k).getDate().split("-")[0]) == yearToday) && (Integer.parseInt(events.get(k).getDate().split("-")[2]) == (Integer.valueOf(modelCalendarTable.getValueAt(row, col).toString().split(" ")[0]))))) {                            
                            String startMin;
                            String endMin;
                            if(events.get(k).getStartMin() == 0)
                                startMin = "00";
                            else
                                startMin = Integer.toString(events.get(k).getStartMin());
                            if(events.get(k).getEndMin() == 0)
                                endMin = "00";
                            else
                                endMin = Integer.toString(events.get(k).getEndMin());
                            
                            String startTime = Integer.toString(events.get(k).getStartHour()) + ":" + startMin;
                            String endTime = Integer.toString(events.get(k).getEndHour()) + ":" + endMin;                            
                            String duration = startTime + " - " + endTime;
                            
                            Object[] rowData = {duration, events.get(k).getEventName(), Boolean.FALSE};
                            modelAgendaTable.addRow(rowData);
                            
                            boolean color = false;
                            for(int l = 0; l < 48; l++) {
                                    if(modelActivityTable.getValueAt(l, 0).equals(endTime)) {             
                                        color = false;
                                    }
                                    if(modelActivityTable.getValueAt(l, 0).equals(startTime)) {                                        
                                        modelActivityTable.setValueAt(events.get(k).getEventName(), l, 1);
                                        color = true;
                                    }
                            }
                        }
                    }
                }
                catch (NullPointerException e){
                    e.printStackTrace();
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
        eventButton.addActionListener(new btn_radioEvent());
        taskButton.addActionListener(new btn_radioTask());
        addEvent.addActionListener(new btn_addEvent());

        pane.add(calendarPanel);
        calendarPanel.add(monthLabel);
        //calendarPanel.add(yearLabel);
        //calendarPanel.add(cmbYear);
        calendarPanel.add(startTime);
        calendarPanel.add(endTime);
        calendarPanel.add(btnPrev);
        calendarPanel.add(btnNext);
        calendarPanel.add(scrollCalendarTable);
        
        calendarPanel.add(eventButton);
        calendarPanel.add(taskButton);
        calendarPanel.add(eventName);
        calendarPanel.add(dateArea);
        calendarPanel.add(addEvent);

        calendarPanel.setBounds(0, 0, 640, 780);
        monthLabel.setBounds(320 - monthLabel.getPreferredSize().width / 2, 50, 200, 50);
        yearLabel.setBounds(20, 610, 160, 40);
        cmbYear.setBounds(460, 630, 160, 40);
        startTime.setBounds(290,630, 160, 40);
        endTime.setBounds(460, 630, 160, 40);
        btnPrev.setBounds(20, 50, 100, 50);
        btnNext.setBounds(520, 50, 100, 50);
        scrollCalendarTable.setBounds(20, 100, 600, 487);
        eventButton.setBounds(300, 600, 60, 20);
        taskButton.setBounds(380, 600, 60, 20);
        eventName.setBounds(20, 600, 200, 20);
        dateArea.setBounds(20, 630, 200, 20);
        addEvent.setBounds(45, 670, 150, 50);
        
        bg = new ButtonGroup();
        bg.add(eventButton);
        bg.add(taskButton);

        frmMain.setResizable(false);
        frmMain.setVisible(true);

        GregorianCalendar cal = new GregorianCalendar();
        dayBound = cal.get(GregorianCalendar.DAY_OF_MONTH);
        monthBound = cal.get(GregorianCalendar.MONTH);
        yearBound = cal.get(GregorianCalendar.YEAR);
        monthToday = monthBound;
        yearToday = yearBound;

        String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
        for (int i = 0; i < 7; i++) {
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

        for (int i = yearBound - 100; i <= yearBound + 100; i++) {
            cmbYear.addItem(String.valueOf(i));
        }
        /*
        activityPanel = new JPanel(null);
        pane.add(activityPanel);
        activityPanel.setBounds(0, 0, 1000, 780);
        // activityTable.getParent().setBackground(activityTable.getBackground()); //Set background

        activityTable.getTableHeader().setResizingAllowed(false);
        activityTable.getTableHeader().setReorderingAllowed(false);

        activityTable.setColumnSelectionAllowed(true);
        activityTable.setRowSelectionAllowed(true);
        activityTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        activityTable.setRowHeight(56);
        modelActivityTable.setColumnCount(2);
        modelActivityTable.setRowCount(6);
        modelActivityTable.setValueAt("Yes", 1, 1);
        */
        refreshCalendar(monthBound, yearBound); //Refresh calendar
    }

    public void refreshCalendar(int month, int year) {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int nod, som, i, j;
        Calendar now = Calendar.getInstance();

        btnPrev.setEnabled(true);
        btnNext.setEnabled(true);
        if (month == 0 && year <= yearBound - 10) {
            btnPrev.setEnabled(false);
        }
        if (month == 11 && year >= yearBound + 100) {
            btnNext.setEnabled(false);
        }

        monthLabel.setText(months[month]);
        monthLabel.setBounds(320 - monthLabel.getPreferredSize().width / 2, 50, 360, 50);

        cmbYear.setSelectedItem("" + year);

        for (i = 0; i < 6; i++) {
            for (j = 0; j < 7; j++) {
                modelCalendarTable.setValueAt(null, i, j);
            }
        }

        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        som = cal.get(GregorianCalendar.DAY_OF_WEEK);

        //System.out.println(nod + "/"+ som);
        //vital loop
        for (i = 1; i <= nod; i++) {
            int row = new Integer((i + som - 2) / 7);
            int column = (i + som - 2) % 7;
            //System.out.println(row + " " + column);
            modelCalendarTable.setValueAt(i, row, column);
            
            //System.out.println(events.size());
            for (int k = 0; k < events.size(); k++) {
                if (((Integer.parseInt(events.get(k).getDate().split("-")[1]) - 1 == month) && (Integer.parseInt(events.get(k).getDate().split("-")[0])) == year) && (Integer.parseInt(events.get(k).getDate().split("-")[2]) == i)) {
                    modelCalendarTable.setValueAt(modelCalendarTable.getValueAt(row, column) + " " + events.get(k).getEventName(), row, column);                
                /*
                else if ((events.get(k).isIsHoliday()) && (events.get(k).getMonth() - 1 == month) && (events.get(k).getYear() <= year) && (events.get(k).getDate() == i)) {
                    modelCalendarTable.setValueAt(modelCalendarTable.getValueAt(row, column) + " " + events.get(k).getEventName(), row, column);
                }
                */
            }
            }
        }

        calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new TableRenderer(events));
        // doesn't work, will fix   activityTable.setDefaultRenderer(activityTable.getColumnClass(0), new ActivityTableRenderer(events));
    }
    
    public boolean isConflicting(EventsObject e) {
        boolean verdict = false;
        int startTemp, endTemp;
        startTemp = (e.getStartHour() * 100 + e.getStartMin());
        endTemp = (e.getEndHour() * 100 + e.getEndMin());
        for (int i = 0; i < events.size(); i++) {
            if (e.getEventName().equals(events.get(i).getEventName())) {
                verdict = true;
            }
            
            if (events.get(i).getDate().equals(e.getDate())) {

                if (startTemp >= (events.get(i).getStartHour() * 100 + events.get(i).getStartMin())
                        && startTemp < (events.get(i).getEndHour() * 100 + events.get(i).getEndMin())) {
                    verdict = true;
                }
                if (endTemp > (events.get(i).getStartHour() * 100 + events.get(i).getStartMin())
                        && endTemp <= (events.get(i).getEndHour() * 100 + events.get(i).getEndMin())) {
                    verdict = true;
                }
                /*
                System.out.println(startTemp);
                System.out.println(endTemp);
                System.out.println(events.get(i).getStartHour() * 100 + events.get(i).getStartMin());
                System.out.println(events.get(i).getEndHour() * 100 + events.get(i).getEndMin());
                */
                if (startTemp == (events.get(i).getStartHour() * 100 + events.get(i).getStartMin())
                        && endTemp == (events.get(i).getEndHour() * 100 + events.get(i).getEndMin())) {
                    verdict = true;
                }
                
                if (startTemp < (events.get(i).getStartHour() * 100 + events.get(i).getStartMin())
                        && endTemp > (events.get(i).getStartHour() * 100 + events.get(i).getStartMin())) {
                    verdict = true;
                }
                if (startTemp < (events.get(i).getEndHour() * 100 + events.get(i).getEndMin())
                        && endTemp > (events.get(i).getEndHour() * 100 + events.get(i).getEndMin())) {
                    verdict = true;
                }
            }

        }
        
        if (startTemp >= endTemp) {
            verdict = true;
        }

        return verdict;
    }
        
    class btn_markDone implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }
    
    class btn_removeEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i = 0; i < agendaTable.getRowCount(); i++) {
                if((Boolean)(agendaTable.getValueAt(i, 2)) == true) {
                    controller.removeData(String.valueOf(agendaTable.getValueAt(i, 1)));
                }
            }
        }
    }
    
    class btn_addEvent implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //make a temporary event so that we can check if its ok
            boolean safe = false;
            EventsObject temp = new EventsObject();
            temp.setEventName(eventName.getText());
            /*
            0 - for event
            1 - for task
            2 - for finished task
            */
            if(eventButton.isSelected()){
                temp.setType(0);
                temp.setColor("Blue");
            }
            else if(taskButton.isSelected()){
                temp.setType(1);
                temp.setColor("Green");
            }
            
            if (temp.getType() == 0) {
                temp.setStartHour(Integer.parseInt(startTime.getSelectedItem().toString().split(":")[0]));
                temp.setStartMin(Integer.parseInt(startTime.getSelectedItem().toString().split(":")[1]));
                temp.setEndHour(Integer.parseInt(endTime.getSelectedItem().toString().split(":")[0]));
                temp.setEndMin(Integer.parseInt(endTime.getSelectedItem().toString().split(":")[1]));
            } else if (temp.getType() == 1) {
                temp.setStartHour(Integer.parseInt(startTime.getSelectedItem().toString().split(":")[0]));
                temp.setStartMin(Integer.parseInt(startTime.getSelectedItem().toString().split(":")[1]));
                temp.setEndHour(Integer.parseInt(startTime.getSelectedItem().toString().split(":")[0]));
                temp.setEndMin(Integer.parseInt(startTime.getSelectedItem().toString().split(":")[1]) + 30);
                if (temp.getEndMin() == 60) {
                    temp.setEndHour(Integer.parseInt(startTime.getSelectedItem().toString().split(":")[0]) + 1);
                    temp.setEndMin(0);
                }
            }
            
            if (!dateArea.getText().equals("")){
                temp.setDate(dateArea.getText());
            }
            else{
                temp.setDate(null);
            }
            
            //check if ok
            if(!isConflicting(temp)){
                safe = true;
            }
            if (safe == true){
                controller.addData(temp);
            }
        }
    }

    class btn_radioTask implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            endTime.setVisible(false);
        }
    }
    
    class btn_radioEvent implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            endTime.setVisible(true);
        }
    }
    class btnPrev_Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (monthToday == 0) {
                monthToday = 11;
                yearToday -= 1;
            } else {
                monthToday -= 1;
            }
            refreshCalendar(monthToday, yearToday);
        }
    }

    class btnNext_Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (monthToday == 11) {
                monthToday = 0;
                yearToday += 1;
            } else {
                monthToday += 1;
            }
            refreshCalendar(monthToday, yearToday);
        }
    }

    class cmbYear_Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (cmbYear.getSelectedItem() != null) {
                String b = cmbYear.getSelectedItem().toString();
                yearToday = Integer.parseInt(b);
                refreshCalendar(monthToday, yearToday);
            }
        }
    }
}
