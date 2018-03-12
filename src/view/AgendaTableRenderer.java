/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import model.EventsObject;

/**
 *
 * @author Arturo III
 */
public class AgendaTableRenderer extends DefaultTableCellRenderer
{        
    private ArrayList<EventsObject> events;
    private int selected;
    
    public AgendaTableRenderer(ArrayList<EventsObject> e, int selected) {
        this.events = e;
        this.selected = selected;
    }
    
    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column)
    {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            if (column == 0 || column == 6)
                    setBackground(new Color(220,220,255));
            else
                    setBackground(Color.WHITE);
            setBorder(null);
            setForeground(Color.black);
            return this;  
    }
}





/*


        
        if(calendarTable.getSelectedRow() != -1) {
            String dayString = ((String)(calendarTable.getValueAt(calendarTable.getSelectedRow(), calendarTable.getSelectedColumn())));
            int spaceCheck = dayString.indexOf(" ");
            System.out.println(dayString);
            
            if(spaceCheck != -1) {
                for(int k = 0; k < events.size(); k++) {
                    if(((Integer.parseInt(events.get(k).getDate().split("-")[1]) - 1 == month) && (Integer.parseInt(events.get(k).getDate().split("-")[0]) == year) && (Integer.parseInt(events.get(k).getDate().split("-")[2]) == Integer.parseInt(dayString.substring(0, spaceCheck)) ))) {
                        System.out.println("ok");
                    }
                }
            }

        }


*/
