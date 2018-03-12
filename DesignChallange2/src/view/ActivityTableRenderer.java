package view;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.logging.*;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import model.EventsObject;

/**
 *
 * @author Arturo III
 */
public class ActivityTableRenderer extends JTextPane implements TableCellRenderer {

    private ArrayList<EventsObject> events;
    private CalendarProgram view;

    public ActivityTableRenderer(ArrayList<EventsObject> e, CalendarProgram view) {
        this.events = e;
        this.view = view;
    } 
    
    private int TimeToRowNumber(String time) {

        switch (time) {

            case "0:30":
                return 1;

            case "1:0":
                return 2;

            case "1:30":
                return 3;

            case "2:0":
                return 4;

            case "2:30":
                return 5;

            case "3:0":
                return 6;

            case "3:30":
                return 7;

            case "4:0":
                return 8;

            case "4:30":
                return 9;

            case "5:0":
                return 10;

            case "5:30":
                return 11;

            case "6:0":
                return 12;

            case "6:30":
                return 13;

            case "7:0":
                return 14;

            case "7:30":
                return 15;

            case "8:0":
                return 16;

            case "8:30":
                return 17;

            case "9:0":
                return 18;

            case "9:30":
                return 19;

            case "10:0":
                return 20;

            case "10:30":
                return 21;

            case "11:0":
                return 22;

            case "11:30":
                return 23;

            case "12:0":
                return 24;

            case "12:30":
                return 25;

            case "13:0":
                return 26;

            case "13:30":
                return 27;

            case "14:0":
                return 28;

            case "14:30":
                return 29;

            case "15:0":
                return 30;

            case "15:30":
                return 31;

            case "16:0":
                return 32;

            case "16:30":
                return 33;

            case "17:0":
                return 34;

            case "17:30":
                return 35;

            case "18:0":
                return 36;

            case "18:30":
                return 37;

            case "19:0":
                return 38;

            case "19:30":
                return 39;

            case "20:0":
                return 40;

            case "20:30":
                return 41;

            case "21:0":
                return 42;

            case "21:30":
                return 43;

            case "22:0":
                return 44;

            case "22:30":
                return 45;

            case "23:0":
                return 46;

            case "23:30":
                return 47;

            //if time is 0:00    
            default:
                return 0;
        }
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        StyledDocument doc = this.getStyledDocument();
        Style style = this.addStyle("test", null);
        //StyleConstants.setForeground(style, Color.black);
        //super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        
        setBorder(null);
        try {
            doc.remove(0, doc.getLength());
        } catch (BadLocationException e) {
            Logger.getLogger(TableRenderer.class.getName()).log(Level.SEVERE, null, e);
        }
        
        String str = String.valueOf(value);
        if (value != null && column == 1){
            try {
                if(!str.contains(" "))
                    doc.insertString(doc.getLength(), str, style);
                else
                    doc.insertString(doc.getLength(), str.substring(0, str.indexOf(" ")), style);
                
                for (int i = 0; i < events.size(); i++) {
                    if (events.get(i).getColor().equals("Green")) {
                        for (int a = TimeToRowNumber(events.get(i).getStartHour() +":"+events.get(i).getStartMin()); a < TimeToRowNumber(events.get(i).getEndHour() +":"+events.get(i).getEndMin()); a++)
                            if ( row == a && view.dateArea.getText().split("-")[2].equals(events.get(i).getDate().split("-")[2]))
                                setBackground(Color.GREEN);
                         
                    } else if (events.get(i).getColor().equals("Blue")) {
                        for (int a = TimeToRowNumber(events.get(i).getStartHour() +":"+events.get(i).getStartMin()); a < TimeToRowNumber(events.get(i).getEndHour() +":"+events.get(i).getEndMin()); a++)
                            if ( row == a && view.dateArea.getText().split("-")[2].equals(events.get(i).getDate().split("-")[2])){
                                setBackground(Color.BLUE);
                                System.out.println(a);
                            }
                    } else {
                        setBackground(Color.WHITE);
                    }
                    
                }
            } catch (BadLocationException ex) {
                Logger.getLogger(ActivityTableRenderer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        //System.out.println(value.toString());
        /*
        System.out.println(String.valueOf(value));
        if (value != null) {
            for (int k = 0; k < events.size(); k++) {
                    if (value.equals(events.get(k).getEventName())) {
                        if("Green".equals(events.get(k).getColor()))
                            setBackground(Color.GREEN);
                        else
                            setBackground(Color.BLUE);
                }
            }
        }
*//*
        if (selected){
            if (selected) {
            setBackground(Color.LIGHT_GRAY);
        }
        }
        */
        return this;
    }

}
