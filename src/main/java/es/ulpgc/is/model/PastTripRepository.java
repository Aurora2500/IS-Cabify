package es.ulpgc.is.model;

import java.util.ArrayList;
import java.util.List;

public class PastTripRepository {

    private final List<PastTrip> tripList;

    public PastTripRepository() {
        this.tripList = new ArrayList<>();
    }

    public void add(PastTrip pastTrip) {
        this.tripList.add(pastTrip);
    }

    public List<PastTrip> list() {
        return tripList;
    }
}


