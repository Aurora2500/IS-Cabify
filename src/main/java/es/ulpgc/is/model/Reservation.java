package es.ulpgc.is.model;

import java.time.LocalDateTime;

public class Reservation {
    private LocalDateTime reservationTime;

    public Reservation(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public LocalDateTime reservationTime() {
        return reservationTime;
    }
    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }
}