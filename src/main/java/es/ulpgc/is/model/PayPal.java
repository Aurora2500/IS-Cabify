package es.ulpgc.is.model;

public class PayPal extends PaymentMethod {
    private final String name;

    public PayPal(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public void charge(double amount) {
        System.out.println("Charging " + amount + " to PayPal account: " + name());
    }

    @Override
    public String toString() {
        return "PayPal: " + name();
    }
}
