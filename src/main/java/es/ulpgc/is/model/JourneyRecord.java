package es.ulpgc.is.model;

import java.util.List;

public class JourneyRecord {
    private List<PastTrip> journeyList;

    public JourneyRecord(List<PastTrip> journeyList) {
        this.journeyList = journeyList;
    }

    public List<PastTrip> journeyList() {
        return journeyList;
    }

}
