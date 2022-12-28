package es.ulpgc.is.model;

//Trip: es viaje
public abstract class Trip {
    private final String destinationAddress;
    private final String pickupAddress;

    private final Driver driver;

    public Trip(String pickupAddress, String destinationAddress, Driver driver) {
        this.destinationAddress = destinationAddress;
        this.pickupAddress = pickupAddress;
        this.driver = driver;
    }

    public String destinationAddress() {
        return destinationAddress;
    }

    public String pickupAddress() {
        return pickupAddress;
    }

    public Driver driver() {
        return driver;
    }
}
