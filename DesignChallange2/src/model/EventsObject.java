package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Rolo
 */
public class EventsObject {
        private String eventName;
	private int startHour;
	private int startMin;
	private int endHour;
	private int endMin;
	private String date;
	private String color;
        private int type;
        
        public static final String TABLE = "events";
	public static final String COL_EVENTNAME = "eventName";
	public static final String COL_STARTHOUR = "startHour";
	public static final String COL_STARTMIN = "startMin";
	public static final String COL_ENDHOUR = "endHour";
	public static final String COL_ENDMIN = "endMin";
	public static final String COL_DATE = "date";
	public static final String COL_COLOR = "color";
        public static final String COL_TYPE = "type";

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
        

    @Override
	public String toString() {
		return "event [EventName=" + eventName + ", StartTime=" + startHour + ":" + startMin + ", EndTime=" + endHour + ":" + endMin + ", Date=" + date
				+ ", Color= "+ color +  ",Type=" + type + " ]";
	}
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getStartMin() {
        return startMin;
    }

    public void setStartMin(int startMin) {
        this.startMin = startMin;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getEndMin() {
        return endMin;
    }

    public void setEndMin(int endMin) {
        this.endMin = endMin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
        
        
}
