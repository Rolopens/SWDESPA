import dayobjects.Events;
import java.util.ArrayList;
import java.util.List;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Rolo
 */
abstract public class DataParser {
    protected List<Events> events = new ArrayList<Events>();
    protected String collect;
    protected CalendarProgram program;
    
    abstract void readData();
    public void processData(){
        int i;
        for (i = 0; i < events.size(); i++){
            program.addEventFromParser(events.get(i));
        }
    }
        
    abstract void writeData();

    
}
