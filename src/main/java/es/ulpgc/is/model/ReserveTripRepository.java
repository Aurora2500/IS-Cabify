package es.ulpgc.is.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ReserveTripRepository {
    private List<ReserveTrip> reserveList;

    public ReserveTripRepository() {
        this.reserveList = new ArrayList<>();
    }

    public Stream<ReserveTrip> stream() {
        return reserveList.stream();
    }

    public ReserveTripRepository addAll(List<ReserveTrip> reserves) {
        this.reserveList.addAll(reserves);
        return this;
    }

    public ReserveTripRepository add(ReserveTrip reservation) {
        this.reserveList.add(reservation);
        return this;
    }

    public ReserveTrip removeReserve(int index) {
        return reserveList.remove(index);
    }

}
