
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.List;
import dayobjects.Events;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rolo
 */
public class CSVParser extends DataParser{
    public CSVParser (CalendarProgram owner){
        super.program = owner;
    }
    @Override
    void readData() {
        String csvFile = "Philippine Holidays.csv";
        String line = "";
        String cvsSplitBy = ",";
        String[] parts;
        String[] date;
        
        Events temp;

        try  {
        	BufferedReader br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                 parts = line.split(cvsSplitBy);
                 //temp = Arrays.asList(parts);
                 
                 date = parts[0].split("/");
                 temp = new Events(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), parts[1], parts[2]);
                 temp.setIsHoliday(true);
                 events.add(temp);
                 
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void writeData()  {
        //System.out.println("Output Generated, writing CSV");
		FileWriter writer;
                events = program.getEvents();
        try {
            writer = new FileWriter("Philippine Holidays.csv");
            for (int i = 0; i < events.size(); i++) {
                        if(events.get(i).isIsHoliday() == true){
			writer.write(events.get(i).getMonth() + "/" + events.get(i).getDate() + "/" + events.get(i).getYear());
                        writer.append(',');
                        writer.write(events.get(i).getEventName());
                        writer.append(',');
                        writer.write(events.get(i).getColorName());
			writer.append('\n');
                        }
		}
		writer.close();
        } catch (IOException ex) {
            Logger.getLogger(CSVParser.class.getName()).log(Level.SEVERE, null, ex);
        }
		
    }
    
}
