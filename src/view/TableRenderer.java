package view;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTextPane;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import model.EventsObject;

/**
 *
 * @author Arturo III
 */
public class TableRenderer extends JTextPane implements TableCellRenderer
{
    private ArrayList<EventsObject> events;
    public TableRenderer(ArrayList<EventsObject> e) {
        this.events = e;
    }
    
    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column)
    {
            StyledDocument doc = this.getStyledDocument();
            Style style = this.addStyle("test", null);
            StyleConstants.setForeground(style, Color.black);
            
            // super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            if (column == 0 || column == 6)
                    setBackground(new Color(220,220,255));
            else
                    setBackground(Color.WHITE);
            setBorder(null);
            
                // setForeground(Color.black);
                
            try {
                doc.remove(0, doc.getLength());
            } catch (BadLocationException ex) {
                Logger.getLogger(TableRenderer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(value != null) {                
                String str = String.valueOf(value);
                String sub = str.substring(str.indexOf(" ")+1, str.length());            
                
                try {
                    if(str.indexOf(" ") == -1)
                        doc.insertString(doc.getLength(), str, style);
                    else
                        doc.insertString(doc.getLength(), str.substring(0, str.indexOf(" ")), style);
                
                    for(int x = 0; x < events.size(); x++) {
                        if(sub.contains(events.get(x).getEventName())) {
                            switch((events.get(x).getColor().toUpperCase()).replaceAll("\\s","")) {
                                case "RED":
                                    StyleConstants.setForeground(style, Color.red);
                                    // setForeground(Color.red);
                                    break;
                                case "BLUE":
                                    StyleConstants.setForeground(style, Color.blue);
                                    setForeground(Color.blue);
                                    break;
                                case "GREEN":
                                    StyleConstants.setForeground(style, Color.GREEN);
                                    setForeground(Color.green);
                                    break;
                                default:
                                    setForeground(Color.black);
                            }
                                doc.insertString(doc.getLength(), " " + events.get(x).getEventName(), style);
                        }
                    }
                } catch (BadLocationException ex) {
                    Logger.getLogger(TableRenderer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (selected){
                setBackground(Color.GRAY);
            }
            return this;  
            
    }
    
}
