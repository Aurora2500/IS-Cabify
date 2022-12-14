package es.ulpgc.is.model;

public class PayPal {
    private String name;

    public PayPal(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
