package es.ulpgc.is.model;

import java.time.LocalDateTime;
import java.util.Optional;

//Journey: es trayecto
public class Journey {
    private LocalDateTime arriveTime;
    private LocalDateTime departureTime;
    private Optional <Tip>tip;
    private Optional<Rating> rating;


    public Journey(LocalDateTime arriveTime, LocalDateTime departureTime, Tip tip, Optional<Rating> rating) {
        this.arriveTime = arriveTime;
        this.departureTime = departureTime;
        this.rating = Optional.empty();
        this.tip = Optional.empty();
    }

    public LocalDateTime arriveTime() {
        return arriveTime;
    }

    public void setArriveTime(LocalDateTime arriveTime) {
        this.arriveTime = arriveTime;
    }

    public LocalDateTime departureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
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
}
