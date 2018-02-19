import dayobjects.Events;
import facebook.FBView;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import sms.SMS;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rolo
 */
public class FBObserverFB extends ObserverFB{
    private FBView view;
    private ArrayList<Events> eventsInView;
    public FBObserverFB(CalendarProgram program, FBView view){
        super(program);
        this.view = view;
        eventsInView = new ArrayList<Events>();
        
    }
    
    @Override
    void update(Events e) {
        if (!eventsInView.contains(e)){
        eventsInView.add(e);
        switch((e.getColorName().toUpperCase()).replaceAll("\\s","")) {
                        case "RED":
                           view.showNewEvent(e.getEventName(), e.getMonth(), e.getDate(), e.getYear(), Color.RED);
                            break;
                        case "BLUE":
                            view.showNewEvent(e.getEventName(), e.getMonth(), e.getDate(), e.getYear(), Color.BLUE);
                            break;
                        case "GREEN":
                            view.showNewEvent(e.getEventName(), e.getMonth(), e.getDate(), e.getYear(), Color.GREEN);
                            break;
                        default:
                            view.showNewEvent(e.getEventName(), e.getMonth(), e.getDate(), e.getYear(), Color.BLACK);
                    }
            
        }
        
    }
    
}