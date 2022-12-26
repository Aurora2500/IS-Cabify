package es.ulpgc.is.model;

import java.util.List;

public class TripRecord {
    private List<PastTrip> tripList;

    public void addTrip(PastTrip trip){
        tripList.add(trip);
    }

    public TripRecord(List<PastTrip> tripList) {
        this.tripList = tripList;
    }

    public List<PastTrip> tripList() {
        return tripList;
    }

    public void setTripList(List<PastTrip> tripList) {
        this.tripList = tripList;
    }
}


