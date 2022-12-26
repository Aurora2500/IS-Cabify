package es.ulpgc.is.model;

import java.util.List;

public class JourneyRecord {
    private List<Journey> journeyList;

    public JourneyRecord(List<Journey> journeyList) {
        this.journeyList = journeyList;
    }

    public List<Journey> journeyList() {
        return journeyList;
    }
}
