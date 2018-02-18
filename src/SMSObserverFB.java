

import dayobjects.Events;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import sms.SMS;
import sms.SMSView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rolo
 */
public class SMSObserverFB extends ObserverFB{
    private SMSView view;
    private ArrayList<Events> eventsInView;
    public SMSObserverFB(CalendarProgram program, SMSView view){
        super(program);
        this.view = view;
        eventsInView = new ArrayList<Events>();
    }
    
    @Override
    void update(Events e) {
        if (!eventsInView.contains(e)){
        eventsInView.add(e);
        Calendar calendar = Calendar.getInstance();
        calendar.set(e.getYear(), e.getMonth()-1, e.getDate());
        switch((e.getColorName().toUpperCase()).replaceAll("\\s","")) {
                        case "RED":
                           view.sendSMS(new SMS(e.getEventName(), calendar, Color.RED));
                            break;
                        case "BLUE":
                            view.sendSMS(new SMS(e.getEventName(), calendar, Color.BLUE));
                            break;
                        case "GREEN":
                            view.sendSMS(new SMS(e.getEventName(), calendar, Color.GREEN));
                            break;
                        default:
                            view.sendSMS(new SMS(e.getEventName(), calendar, Color.BLACK));
                    }
            
        }
    }
    
}
