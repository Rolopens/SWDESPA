package traffic;

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
        System.out.println("Board changed: " + arg);
    }
    
}
