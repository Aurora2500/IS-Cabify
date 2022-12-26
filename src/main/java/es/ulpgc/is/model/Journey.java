package es.ulpgc.is.model;

import java.time.LocalDateTime;
import java.util.Optional;

//Journey: es trayecto
public class Journey {
    private LocalDateTime arriveTime;
    private LocalDateTime departureTime;
    private Optional <Tip> tip;


    public Journey(LocalDateTime arriveTime, LocalDateTime departureTime, Tip tip) {
        this.arriveTime = arriveTime;
        this.departureTime = departureTime;
        this.tip = Optional.empty();
    }


    public void JourneyTip(Tip tip){
       new PaymentManager().PayTip(tip.getTip());
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
}
