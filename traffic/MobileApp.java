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
        System.out.println("Mobile App 1");
        System.out.println("Metro Cebu");
        
        for(int i = 0; i < ((ArrayList<Road>)arg).size(); i++) {            
            System.out.println(((ArrayList<Road>)arg).get(i).getNorthCondition() + " (NB) - !" + ((ArrayList<Road>)arg).get(i).getNorthAdvisory() + "! - " + ((ArrayList<Road>)arg).get(i).getRoadName() + 
            " - !" + ((ArrayList<Road>)arg).get(i).getSouthAdvisory() +  "! - (SB) " + ((ArrayList<Road>)arg).get(i).getSouthCondition());
        }
    }
}
