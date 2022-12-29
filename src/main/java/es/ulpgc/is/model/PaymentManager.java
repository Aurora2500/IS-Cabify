package es.ulpgc.is.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PaymentManager {
    private int activePayment;
    private final List<Double> promo = new ArrayList<>();
    private final List<PaymentMethod> payments = new ArrayList<>();
    private final static Map<String, Double> validCodes = Map.of(
            "CABIFYPROMO", 0.1,
            "CABIFY15", 0.15,
            "CABIFY20", 0.2,
            "CABIFY30", 0.3);


    private void charge(double ammount) {
        payments.get(activePayment).charge(ammount);
    }

    public List<PaymentMethod> getPayments() {
        return payments;
    }

    public boolean redeemCode(String code) {
        Double discount = validCodes.get(code);
        if (discount != null){
            promo.add(discount);
        }
        return discount != null;
    }

    public double getDiscount() {
        double discount = 1;
        for (Double d : promo) {
            discount *= 1 - d;
        }
        return 1 - discount;
    }

    public void PayFare(double price) {
        double finalPrice = price;
        for (double discount : promo) {
            finalPrice *= 1 - discount;
        }
        promo.clear();
        charge(finalPrice);
    }

    public void PayTip(double ammount) {
        charge(ammount);
    }


    public void addPayment(PaymentMethod paymentMethod){
        payments.add(paymentMethod);
    }

    public void removePayment(PaymentMethod paymentMethod){
        payments.remove(paymentMethod);
    }

    public PaymentMethod activePayment(){
        return payments.get(activePayment);
    }

    public void setActivePayment(int activePayment){
        this.activePayment = activePayment;
    }

}
