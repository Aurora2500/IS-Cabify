package es.ulpgc.is.model;

public class BankCard extends PaymentMethod {
    private String cardCode;

    public BankCard(String cardCode) {
        this.cardCode = cardCode;
    }

    public String cardCode() {
        return cardCode;
    }

    @Override
    public void charge(double ammount) {

    }


}
