package es.ulpgc.is.model;

public class BankCard extends PaymentMethod {
    private final String iban;

    public BankCard(String iban) {
        this.iban = iban;
    }

    public String iban() {
        return iban;
    }

    @Override
    public void charge(double amount) {
        System.out.println("Charging " + amount + " to IBAN number: " + iban);
    }

    @Override
    public String toString() {
        return "Bank card: " + iban();
    }
}
