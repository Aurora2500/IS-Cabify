package es.ulpgc.is.model;

public class Tip {
    private Driver driver;
    private double tip;

    public Tip(double tip, Driver driver) {
        this.driver = driver;
        this.tip = tip;
    }

    public double tip() {
        return tip;
    }

    public Driver driver() {
        return driver;
    }
}
