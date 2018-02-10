package traffic;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Antonio Miguel B. Llamas, Romeo Manuel N. Pena
 */
public class WebApp implements Observer {
   
    @Override
    public void update(Observable o, Object arg) 
    {           
        System.out.println("\nWeb App 1");
        System.out.println("Metro Cebu");
        
        System.out.printf("%-25s %-25s %-25s %s", "", "NB", "SB", "\n");
        for(int i = 0; i < ((ArrayList<Road>)arg).size(); i++) {
            String NB = (((ArrayList<Road>)arg).get(i).getNorthCondition() + " (" + ((ArrayList<Road>)arg).get(i).getNorthAdvisory() + "!)");
            String SB = (((ArrayList<Road>)arg).get(i).getSouthCondition() + " (" + ((ArrayList<Road>)arg).get(i).getSouthAdvisory() + "!)");
            System.out.printf("%-25s %-25s %-25s %s", ((ArrayList<Road>)arg).get(i).getRoadName(), NB, SB, "\n");        
        }
        System.out.println();
    }
}
