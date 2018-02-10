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
        System.out.println("Web App 1");
        System.out.println("Metro Cebu");
        
        System.out.printf("%s %-30s %-10s", "", "NB", "SB\n");
        for(int i = 0; i < ((ArrayList<Road>)arg).size(); i++) {
            System.out.printf("%s %-20s %s %s %s %-10s %s %s %s", ((ArrayList<Road>)arg).get(i).getRoadName(), ((ArrayList<Road>)arg).get(i).getNorthCondition(), " (", ((ArrayList<Road>)arg).get(i).getNorthAdvisory(), "!)", ((ArrayList<Road>)arg).get(i).getSouthCondition(), " (", ((ArrayList<Road>)arg).get(i).getSouthAdvisory(), "!)\n");        
        }
    }
}
