
import facebook.FBView;
import sms.SMSView;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Arturo III
 */
public class DesignChallenge1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CalendarProgram cp = new CalendarProgram();
        cp.attachObserver(new SMSObserverFB(cp, new SMSView()));
        cp.attachObserver(new FBObserverFB(cp, new FBView()));
    }
}
