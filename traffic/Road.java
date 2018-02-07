package traffic;

/**
 *
 * @author Antonio Miguel B. Llamas, Romeo Manuel N. Pena
 */
public class Road {
    private String northBound;
    private String southBound;
    private String roadName;
    private String northAdvisory;
    private String southAdvisory;
    private String northStatus;
    private String southStatus;

    public void setNorthBound(String northBound) {
        this.northBound = northBound;
    }

    public void setSouthBound(String southBound) {
        this.southBound = southBound;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public void setNorthAdvisory(String northAdvisory) {
        this.northAdvisory = northAdvisory;
    }

    public void setSouthAdvisory(String southAdvisory) {
        this.southAdvisory = southAdvisory;
    }

    public void setNorthStatus(String northStatus) {
        this.northStatus = northStatus;
    }

    public void setSouthStatus(String southStatus) {
        this.southStatus = southStatus;
    }

    public String getNorthBound() {
        return northBound;
    }

    public String getSouthBound() {
        return southBound;
    }

    public String getRoadName() {
        return roadName;
    }

    public String getNorthAdvisory() {
        return northAdvisory;
    }

    public String getSouthAdvisory() {
        return southAdvisory;
    }

    public String getNorthStatus() {
        return northStatus;
    }

    public String getSouthStatus() {
        return southStatus;
    }
    
    public Road(String name) {
        this.roadName = name;
    }
}
