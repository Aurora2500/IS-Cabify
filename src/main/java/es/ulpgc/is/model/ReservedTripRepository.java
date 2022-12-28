package es.ulpgc.is.model;

import java.util.ArrayList;
import java.util.List;

public class ReservedTripRepository {
    private final List<ReservedTrip> reserveList;

    public ReservedTripRepository() {
        this.reserveList = new ArrayList<>();
    }

    public List<ReservedTrip> list() {
        return reserveList;
    }

    public void add(ReservedTrip reservation) {
        this.reserveList.add(reservation);
    }

    public ReservedTrip removeReserve(int index) {
        return reserveList.remove(index);
    }

}
