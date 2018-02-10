package traffic;

/**
 *
 * @author Antonio Miguel B. Llamas, Romeo Manuel N. Pena
 */
public class Road {
    private String roadName;
    private String northAdvisory;
    private String southAdvisory;
    private String northCondition;
    private String southCondition;

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public void setNorthAdvisory(String northAdvisory) {
        this.northAdvisory = northAdvisory;
    }

    public void setSouthAdvisory(String southAdvisory) {
        this.southAdvisory = southAdvisory;
    }

    public void setNorthCondition(String northCondition) {
        this.northCondition = northCondition;
    }

    public void setSouthCondition(String southCondition) {
        this.southCondition = southCondition;
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

    public String getNorthCondition() {
        return northCondition;
    }

    public String getSouthCondition() {
        return southCondition;
    }
    
    public Road(String name) {
        this.roadName = name;
    }
}
