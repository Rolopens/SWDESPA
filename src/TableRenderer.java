/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import dayobjects.Events;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Arturo III
 */
public class TableRenderer extends DefaultTableCellRenderer
{
    private ArrayList<Events> events;
    public TableRenderer(ArrayList<Events> e) {
        this.events = e;
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
                        
            String str = String.valueOf(value);
            String sub = str.substring(str.indexOf(" ")+1, str.length());            
            
            for(int x = 0; x < events.size(); x++) {
                if(sub.contains(events.get(x).getEventName())) {
                    switch((events.get(x).getColorName().toUpperCase()).replaceAll("\\s","")) {
                        case "RED":
                            setForeground(Color.red);
                            break;
                        case "BLUE":
                            setForeground(Color.blue);
                            break;
                        case "GREEN":
                            setForeground(Color.green);
                            break;
                        default:
                            setForeground(Color.black);
                    }
                }
            }                            
            
            if (selected){
                setBackground(Color.GRAY);
            }
            return this;  
            
    }
    
}
