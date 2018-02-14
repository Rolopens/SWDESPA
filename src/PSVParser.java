
import dayobjects.Events;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
public class PSVParser extends DataParser{
    
    public PSVParser(CalendarProgram owner){
        super.program = owner;
    }
    @Override
    void readData() {
        String csvFile = "DLSU Unicalendar.psv";
        String line = "";
        String[] parts;
        String[] date;
        
        Events temp;

        try  {
        	BufferedReader br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                
                 parts = line.split(" \\| ");
                 
                 //temp = Arrays.asList(parts);
                 date = parts[1].split("/");
                 
                 temp = new Events(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), parts[0], parts[2]);
                 temp.setIsHoliday(false);
                 events.add(temp);
                 
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void writeData() {
        //System.out.println("Output Generated, writing CSV");
		FileWriter writer;
                events = program.getEvents();
        try {
            writer = new FileWriter("DLSU Unicalendar.psv");
            for (int i = 0; i < events.size(); i++) {
			//System.out.println(data.get(i).toString());
			//collect = events.get(i).stream().collect(Collectors.joining(","));
			//writer.write(collect);
                        if(events.get(i).isIsHoliday() == false){
			
                        writer.write(events.get(i).getEventName());
                        writer.write(" | ");
                        writer.write(events.get(i).getMonth() + "/" + events.get(i).getDate() + "/" + events.get(i).getYear());
                        writer.write(" | ");
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
