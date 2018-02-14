
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rolo
 */
public class addEvent extends JFrame {
    //gui components
    private JTextField eventName;
    private JComboBox colors;
    private JButton submitButton;
    private JRadioButton isHoliday;
    private JButton cancelButton;
    private JPanel panel1;
    private JPanel panel2;
    
    private CalendarProgram program;
    
    
    public addEvent(CalendarProgram owner){
        super("Add Event");
        this.program = owner;
        String[] color = {"Red", "Green", "Blue"};
        this.setLayout(new BorderLayout());
        panel1 = new JPanel();
        panel2 = new JPanel();
        
        this.add(panel1, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.CENTER);
        
        eventName = new JTextField();
        eventName.setPreferredSize(new Dimension(150, 30));
        colors = new JComboBox(color);
        isHoliday = new JRadioButton("Holiday");
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");
        
        panel1.add(eventName);
        panel1.add(colors);
        panel2.add(isHoliday);
        panel2.add(submitButton);
        panel2.add(cancelButton);
        
        setSize(300, 110);
        setLocation(600, 300);
        setVisible(true);
        setResizable(false);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
