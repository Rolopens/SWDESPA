package traffic;

import java.util.Scanner;

/**
 *
 * @author Antonio Miguel B. Llamas, Romeo Manuel N. Pena
 */
public class Traffic {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int x;
        int response = 0;        
        
        Dashboard dashboard = new Dashboard();
        
        System.out.println("Welcome to DOTC Metro Cebu Navigator!");
            
        while(response != 5) {                
            System.out.println("[1] Add Mobile App");
            System.out.println("[2] Add Web App");
            System.out.println("[3] Update Northbound of a Road");
            System.out.println("[4] Update Southbound of a Road");
            System.out.println("[5] Stop");

            response = sc.nextInt();

            if(response == 1) {
                dashboard.addObserver(new MobileApp());
                System.out.println("Mobile App 1 was added!");
            }

            else if(response == 2) {
                dashboard.addObserver(new WebApp());
                System.out.println("Web App 1 was added!");
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

                for(x = 0; x < dashboard.getRoads().size(); x++) {
                    System.out.print("[");
                    System.out.print(x+1);
                    System.out.print("] ");
                    System.out.println(dashboard.getRoads().get(x).getRoadName());
                }

                while(go == false) {
                    response2 = sc.nextInt();
                    if(response2 >= 1 && response2 <= (dashboard.getRoads().size()))
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
                    System.out.println("How is the traffic?");
                    for(x = 0; x < dashboard.getRoadConditions().size(); x++) {
                        System.out.print("[");
                        System.out.print(x+1);
                        System.out.print("] ");
                        System.out.println(dashboard.getRoadConditions().get(x));
                    }

                    while(go == false) {
                        response4 = sc.nextInt();
                        if(response4 >= 1 && response4 <= dashboard.getRoadConditions().size())
                            go = true;
                        else
                            System.out.println("Invalid input!");
                    }                    
                }

                else {
                    System.out.println("What do you report on the road?");
                    for(x = 0; x < dashboard.getRoadAdvisories().size(); x++) {
                        System.out.print("[");
                        System.out.print(x+1);
                        System.out.print("] ");
                        System.out.println(dashboard.getRoadAdvisories().get(x));
                    }

                    while(go == false) {
                        response4 = sc.nextInt();
                        if(response4 >= 1 && response4 <= dashboard.getRoadAdvisories().size())
                            go = true;
                        else
                            System.out.println("Invalid input!");
                    }
                }
                
                dashboard.updateBoard((response2 - 1), response, response3, (response4 - 1));
            }
            
            else if(response == 5)
                break;

            else {
                System.out.println("Invalid input!");
            }
        }
        
        System.out.println("Exited program.");
    }   
}