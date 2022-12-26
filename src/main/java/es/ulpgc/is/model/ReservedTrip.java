package es.ulpgc.is.model;

import java.time.LocalDateTime;

public class ReservedTrip extends Trip{
    private LocalDateTime reservationTime;

    public ReservedTrip(String destinationAdress, String pickupAdress, LocalDateTime reservationTime) {
        super(destinationAdress, pickupAdress);
        this.reservationTime = reservationTime;
    }

    public LocalDateTime reservationTime() {
        return reservationTime;
    }
    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }
}