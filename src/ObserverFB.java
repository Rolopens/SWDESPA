
import dayobjects.Events;
import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rolo
 */
abstract class ObserverFB {
    protected CalendarProgram subject;
    abstract void update(Events e);

    public ObserverFB(CalendarProgram subject) {
        this.subject = subject;
    }

}
