/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


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
    }
    
    public void passData(){
        view.setEventsArrayList(model.getAll());
    }
}
