package traffic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

/**
 *
 * @author Antonio Miguel B. Llamas, Romeo Manuel N. Pena
 */
public class Dashboard extends Observable {
    
    ArrayList<Road> roads = new ArrayList<Road>();        
    ArrayList<String> roadNames = new ArrayList<>(Arrays.asList("Osmena Boulevard", "Colon", "V. Rama", "Marcelo Fernan Bridge", "Mactan-Mandaue Bridge"));
    ArrayList<String> roadConditions = new ArrayList<>(Arrays.asList("Heavy", "Medium", "Light"));
    ArrayList<String> roadAdvisories = new ArrayList<>(Arrays.asList("Accident", "Flood", "Road Repair", "Road Blocking"));

    public Dashboard() {
        int x;
        for(x = 0; x < roadNames.size(); x++)
            roads.add(new Road(roadNames.get(x)));
        
        for(x = 0; x < roads.size(); x++) {
            roads.get(x).setNorthAdvisory("(n/a)");
            roads.get(x).setSouthAdvisory("(n/a)");
            roads.get(x).setNorthCondition("(n/a)");
            roads.get(x).setSouthCondition("(n/a)");
        }            
    }
    
    public ArrayList<Road> getRoads() {
        return roads;
    }
    
    public ArrayList<String> getRoadConditions() {
        return roadConditions;
    }
    
    public ArrayList<String> getRoadAdvisories() {
        return roadAdvisories;
    }
        
    public void updateBoard(int x, int a, int b, int c) {
        if(a == 3) {
            if(b == 1)                
                this.roads.get(x).setNorthCondition(this.roadConditions.get(c));
            else
                this.roads.get(x).setNorthAdvisory(this.roadAdvisories.get(c));
        }
        else {
            if(b == 1)
                this.roads.get(x).setSouthCondition(this.roadConditions.get(c));
            else
                this.roads.get(x).setSouthAdvisory(this.roadAdvisories.get(c));
        }
                
        setChanged();
        notifyObservers(this.roads);        
    }   
}