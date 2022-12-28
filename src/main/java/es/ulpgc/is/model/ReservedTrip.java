package es.ulpgc.is.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ReservedTrip extends Trip{
    private final LocalDateTime pickupTime;

    public ReservedTrip(String pickupAddress, String destinationAddress, Driver driver, LocalDateTime pickupTime) {
        super(pickupAddress, destinationAddress, driver);
        this.pickupTime = pickupTime;
    }

    public LocalDateTime pickupTime() {
        return pickupTime;
    }
    @Override
    public String toString() {
        return this.pickupAddress() + " - " + this.destinationAddress() + " (" + this.pickupTime().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + ")";
    }
}