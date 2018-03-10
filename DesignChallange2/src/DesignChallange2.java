
import controller.CalendarController;
import model.EventsConnection;
import model.EventsService;
import view.CalendarProgram;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Rolo
 */
public class DesignChallange2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CalendarController c = new CalendarController(new EventsService(new EventsConnection()), new CalendarProgram());
        //EventsService test = new EventsService(new EventsConnection());
        //System.out.println(test.getAll().get(0).getDate().split("-")[1]);
    }
    
}
