package view;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


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
public class ActivityTableRenderer extends DefaultTableCellRenderer
{
    private ArrayList<EventsObject> events;
    public ActivityTableRenderer(ArrayList<EventsObject> e) {
        this.events = e;
    }
    
    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column)
    {
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        for (int k = 0; k < events.size(); k++) {
            if (value != null)
                if(value.equals(events.get(k).getEventName()))                   
                setBackground(Color.RED);
        }
        return this;
    }
    
}
