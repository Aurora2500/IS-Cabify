package es.ulpgc.is.model;

public class PayPal extends PaymentMethod {
    private String name;

    public PayPal(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public void charge(double ammount) {
    }
}
