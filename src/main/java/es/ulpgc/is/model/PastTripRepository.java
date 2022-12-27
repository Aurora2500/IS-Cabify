package es.ulpgc.is.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PastTripRepository {

    private List<PastTrip> tripList;

    public PastTripRepository() {
        this.tripList = new ArrayList<>();
    }

    public Stream<PastTrip> stream() {
        return tripList.stream();
    }

    public PastTripRepository addAll(List<PastTrip> pastTrips) {
        this.tripList.addAll(pastTrips);
        return this;
    }

    public PastTripRepository add(List<PastTrip> pastTrip) {
        this.tripList.addAll(pastTrip);
        return this;
    }

}


