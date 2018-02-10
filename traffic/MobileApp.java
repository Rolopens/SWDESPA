package traffic;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Antonio Miguel B. Llamas, Romeo Manuel N. Pena
 */
public class MobileApp implements Observer {
        
    @Override
    public void update(Observable o, Object arg) 
    {           
        System.out.println("\nMobile App 1");
        System.out.println("Metro Cebu");
        
        for(int i = 0; i < ((ArrayList<Road>)arg).size(); i++) {

        String NB;
        String SB;
            
            if(!(((ArrayList<Road>)arg).get(i).getNorthAdvisory()).equals("(n/a)"))            
                NB = " (NB) - !" + ((ArrayList<Road>)arg).get(i).getNorthAdvisory() + "! - ";
            else
                NB = " (NB) - ";
            
            if(!(((ArrayList<Road>)arg).get(i).getSouthAdvisory()).equals("(n/a)"))            
                SB = " - !" + ((ArrayList<Road>)arg).get(i).getSouthAdvisory() +  "! - (SB) ";
            else
                SB = " - (SB) ";
            
            System.out.println(((ArrayList<Road>)arg).get(i).getNorthCondition() + NB + ((ArrayList<Road>)arg).get(i).getRoadName() + 
            SB + ((ArrayList<Road>)arg).get(i).getSouthCondition());
        }
        System.out.println();
    }
}