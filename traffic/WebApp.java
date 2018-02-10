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

        String NB;
        String SB;
            
            if(!(((ArrayList<Road>)arg).get(i).getNorthAdvisory()).equals("(n/a)"))            
                NB = (((ArrayList<Road>)arg).get(i).getNorthCondition() + " (" + ((ArrayList<Road>)arg).get(i).getNorthAdvisory() + "!)");
            else
                NB = (((ArrayList<Road>)arg).get(i).getNorthCondition());
            
            if(!(((ArrayList<Road>)arg).get(i).getSouthAdvisory()).equals("(n/a)"))
                SB = (((ArrayList<Road>)arg).get(i).getSouthCondition() + " (" + ((ArrayList<Road>)arg).get(i).getSouthAdvisory() + "!)");
            else
                SB = (((ArrayList<Road>)arg).get(i).getSouthCondition());


            System.out.printf("%-25s %-25s %-25s %s", ((ArrayList<Road>)arg).get(i).getRoadName(), NB, SB, "\n");        
        }
        System.out.println();
    }
}