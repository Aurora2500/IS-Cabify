package es.ulpgc.is.model;

//Trip: es viaje
public abstract class Trip {
    private String destinationAdress;
    private String pickupAdress;

    protected Trip(String destination, String place) {
        this.destinationAdress = destination;
        this.pickupAdress = place;
    }

    public String destination() {
        return destinationAdress;
    }

    public void setDestinationAdress(String destinationAdress) {
        this.destinationAdress = destinationAdress;
    }

    public String place() {
        return pickupAdress;
    }

    public void setPlace(String place) {
        this.pickupAdress = place;
    }
}
