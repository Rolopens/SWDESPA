package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rolo
 */
public class EventsService {

    private EventsConnection connection;

    public EventsService(EventsConnection connection) {
        this.connection = connection;
    }

    private EventsObject toEventObject(ResultSet rs) throws SQLException {
        EventsObject event = new EventsObject();

        //User.setUsername(rs.getString(User.COL_USERNAME));
        event.setEventName(rs.getString(EventsObject.COL_EVENTNAME));
        event.setStartHour(rs.getInt(EventsObject.COL_STARTHOUR));
        event.setStartMin(rs.getInt(EventsObject.COL_STARTMIN));
        event.setEndHour(rs.getInt(EventsObject.COL_ENDHOUR));
        event.setEndMin(rs.getInt(EventsObject.COL_ENDMIN));
        event.setDate(rs.getString(EventsObject.COL_DATE));
        event.setColor(rs.getString(EventsObject.COL_COLOR));
        event.setType(rs.getInt(EventsObject.COL_TYPE));

        return event;
    }

    public ArrayList<EventsObject> getAll() {
        ArrayList<EventsObject> events = new ArrayList<EventsObject>();

        Connection cnt = connection.getConnection();

        //create string query
        String query = "SELECT * FROM " + EventsObject.TABLE + " ORDER BY " + EventsObject.COL_DATE + ", " + EventsObject.COL_STARTHOUR + ", " + EventsObject.COL_STARTMIN;
        try {
            //create prepared statement
            PreparedStatement ps = cnt.prepareStatement(query);

            //get result and store in result set
            ResultSet rs = ps.executeQuery();

            //transform set to list
            while (rs.next()) {
                events.add(toEventObject(rs));
            }

            ps.close();
            rs.close();
            cnt.close();

            System.out.println("[UserS] SELECT SUCCESS!");
        } catch (SQLException e) {
            System.out.println("[UserS] SELECT FAILED!");
            e.printStackTrace();
        }

        return events;
    }

    public void addUser(EventsObject e) {
        //get connection
        Connection cnt = connection.getConnection();

        //create query
        String query = "INSERT INTO " + EventsObject.TABLE + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            //create prepared statement
            PreparedStatement ps = cnt.prepareStatement(query);

            //prepare the values
            ps.setString(1, e.getEventName());
            ps.setInt(2, e.getStartHour());
            ps.setInt(3, e.getStartMin());
            ps.setInt(4, e.getEndHour());
            ps.setInt(5, e.getEndMin());
            ps.setString(6, e.getDate());
            ps.setString(7, e.getColor());
            ps.setInt(8, e.getType());
            
            
            

            //execute the update
            ps.executeUpdate();

            //close resources
            ps.close();
            cnt.close();

            System.out.println("[UserS] INSERTION SUCCESS :3!");
        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            System.out.println("[UserS] INSERTION FAILED! :(");
            ex.printStackTrace();
        }

    }

    public void removeUser(String eventName) {
        //get connection
        Connection cnt = connection.getConnection();

        //create query
        String query = "DELETE FROM " + EventsObject.TABLE + " WHERE eventName = ?";

        try {
            //create prepared statement
            PreparedStatement ps = cnt.prepareStatement(query);

            //prepare the values
            ps.setString(1, eventName);

            //execute the update
            ps.executeUpdate();

            //close resources
            ps.close();
            cnt.close();

            System.out.println("[UserS] DELETE SUCCESS :3!");
        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            System.out.println("[UserS] DELETE FAILED! :(");
            ex.printStackTrace();
        }

    }
}
