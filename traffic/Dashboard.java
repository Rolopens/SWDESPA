/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traffic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Scanner;

/**
 *
 * @author Miggy Llamas
 */
public class Dashboard extends Observable {
    public void updateBoard(int x, int response1, int response2, String value, ArrayList<Road> array) {
        setChanged();
        
        if(response1 == 3) {
            if(response2 == 1)
                notifyObservers(array.get(x).getNorthStatus());
            else
                notifyObservers(array.get(x).getNorthAdvisory());
        }
        else {
            if(response2 == 1)
                notifyObservers(array.get(x).getSouthStatus());
            else
                notifyObservers(array.get(x).getSouthAdvisory());
        }
                                
            /* if(!array.get(x).getNorthAdvisory().equals(value)) {                
                System.out.println("Value changed!");
                array.get(x).setNorthAdvisory(value);
            } */                        
    }
    
    public void run() {
        Scanner sc = new Scanner(System.in);
        int x;
        int response;        
        
        ArrayList<Road> roads = new ArrayList<Road>();
        
        ArrayList<String> roadNames = new ArrayList<>(Arrays.asList("Osmena Boulevard", "Colon", "V. Rama", "Marcelo Fernan Bridge", "Mactan-Mandaue Bridge"));
        ArrayList<String> roadConditions = new ArrayList<>(Arrays.asList("Heavy", "Medium", "Light"));
        ArrayList<String> roadAdvisories = new ArrayList<>(Arrays.asList("Accident", "Flood", "Road Repair", "Road Blocking"));
        
        ArrayList<MobileApp> mobileApps = new ArrayList<MobileApp>();
        // ArrayList<WebApp> webApps = new ArrayList<WebApp>();
        
        for(x = 0; x < roadNames.size(); x++)
            roads.add(new Road(roadNames.get(x)));
        
        System.out.println("Welcome to DOTC Metro Cebu Navigator!");
        System.out.println("[1] Add Mobile App");
        System.out.println("[2] Add Web App");
        System.out.println("[3] Update Northbound of a Road");
        System.out.println("[4] Update Southbound of a Road");
        
        response = sc.nextInt();
        
        if(response == 1) {
            mobileApps.add(new MobileApp());
            this.addObserver(mobileApps.get(mobileApps.size() - 1));
            System.out.println("Mobile App " + mobileApps.size() + " was added!");
        }
        
        else if(response == 2) {
            /* webApps.add(new WebApp());
            this.addObserver(webApps.get(webApps.size() - 1));
            System.out.println("Web App " + webApps.size() + " was added!"); */
        }
        
        else if(response == 3 || response == 4) {
            int response2 = 0;
            int response3 = 0;
            int response4 = 0;
            boolean go = false;
            
            if(response == 3)
                System.out.println("What NB road?");
            else
                System.out.println("What SB road?");
                                   
            for(x = 0; x < roads.size(); x++) {
                System.out.print("[");
                System.out.print(x+1);
                System.out.print("] ");
                System.out.println(roads.get(x).getRoadName());
            }
            
            while(go == false) {
                response2 = sc.nextInt();
                if(response2 >= 1 && response2 <= (roads.size()))
                    go = true;
                else
                    System.out.println("Invalid input!");
            }
            
            go = false;
            System.out.println("What information?");
            System.out.println("[1] Traffic Condition");
            System.out.println("[2] Traffic Advisory");
            
            while(go == false) {
                response3 = sc.nextInt();
                if(response3 >= 1 && response3 <= 2)
                    go = true;
                else
                    System.out.println("Invalid input!");
            }
            go = false;
            
            if(response3 == 1) {
                System.out.println("How heavy is the traffic?");
                for(x = 0; x < roadConditions.size(); x++) {
                    System.out.print("[");
                    System.out.print(x+1);
                    System.out.print("] ");
                    System.out.println(roadConditions.get(x));
                }
                                
                while(go == false) {
                    response4 = sc.nextInt();
                    if(response4 >= 1 && response4 <= roadConditions.size())
                        go = true;
                    else
                        System.out.println("Invalid input!");
                }
                
                if(response == 3)                
                    roads.get(response2 - 1).setNorthStatus(roadConditions.get(response4 - 1));
                else
                    roads.get(response2 - 1).setSouthStatus(roadConditions.get(response4 - 1));
                
                this.updateBoard((response2 - 1), response, response3, (roadConditions.get(response4 - 1)), roads);
            }
            
            else {
                System.out.println("What happened on the road?");
                for(x = 0; x < roadAdvisories.size(); x++) {
                    System.out.print("[");
                    System.out.print(x+1);
                    System.out.print("] ");
                    System.out.println(roadAdvisories.get(x));
                }
                                
                while(go == false) {
                    response4 = sc.nextInt();
                    if(response4 >= 1 && response4 <= roadAdvisories.size())
                        go = true;
                    else
                        System.out.println("Invalid input!");
                }
                if(response == 3)                    
                    roads.get(response2 - 1).setNorthAdvisory(roadAdvisories.get(response4 - 1));
                else
                    roads.get(response2 - 1).setSouthAdvisory(roadAdvisories.get(response4 - 1));
                                
                this.updateBoard((response2 - 1), response, response3, (roadAdvisories.get(response4 - 1)), roads);
            }                        
        }
        
        else {
            System.out.println("Invalid input!");
        }    
    }   
}
