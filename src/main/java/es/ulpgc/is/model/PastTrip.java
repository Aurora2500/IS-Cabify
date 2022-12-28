package es.ulpgc.is.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class PastTrip extends Trip {
    private final LocalDateTime arrivalTime;
    private final LocalDateTime departureTime;
    private Optional<Tip> tip;
    private Optional<Rating> rating;


    public PastTrip(String destinationAddress, String pickupAddress, Driver driver, LocalDateTime arrivalTime, LocalDateTime departureTime) {
        super(destinationAddress, pickupAddress, driver);
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.rating = Optional.empty();
        this.tip = Optional.empty();
    }

    public LocalDateTime arrivalTime() {
        return arrivalTime;
    }

    public LocalDateTime departureTime() {
        return departureTime;
    }

    public Optional<Tip> tip() {
        return tip;
    }

    public void JourneyTip(Tip tip){
        new PaymentManager().PayTip(tip.getTip());
    }

    public Optional<Rating> rating() {
        return rating;
    }
    public void setRating(Rating rating) {
        this.rating = Optional.of(rating);
    }

    @Override
    public String toString() {
        return this.pickupAddress() + " - " + this.destinationAddress() + " (" + this.arrivalTime().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + ")";
    }
}
