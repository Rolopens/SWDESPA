/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.util.ArrayList;
import java.util.GregorianCalendar;
import model.EventsObject;
import model.EventsService;
import view.CalendarProgram;

/**
 *
 * @author Rolo
 */
public class CalendarController {
    private EventsService model;
    private CalendarProgram view;

    public CalendarController(EventsService model, CalendarProgram view) {
        this.model = model;
        this.view = view;
        passData();
        this.view.setController(this);
    }
    
    private void passData(){
        this.view.setEventsArrayList(model.getAll());
        GregorianCalendar cal = new GregorianCalendar();
        int monthBound = cal.get(GregorianCalendar.MONTH);
        int yearBound = cal.get(GregorianCalendar.YEAR);
        this.view.refreshCalendar(monthBound, yearBound);
        
        /*
        ArrayList<EventsObject> temp = model.getAll();
        for (int i = 0; i < temp.size(); i++)
            System.out.println(temp.get(i).toString());
*/
    }
    
    public void addData(EventsObject e){
        model.addUser(e);
        passData();
        
    }
    
    public void removeData(String eventName){
        model.removeUser(eventName);
        passData();
        
    }
    
    public void updateData(String eventName, int val, String color){
        model.updateUser(eventName, val, color);
        passData();
    }
}
