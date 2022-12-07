package es.ulpgc.is.model;

import java.util.List;
import java.util.Map;

public class PaymentManager {
    int activePayment;
    List<Double> promo;
    List<PaymentMethod> payment;
    static Map<String, Double> validCodes = Map.of(
            "CABIFYPROMO", 0.1,
            "CABIFY15", 0.15,
            "CABIFY20", 0.2,
            "CABIFY30", 0.3);
    public boolean verifycode(String code) {
        Double discount = validCodes.get(code);
        if (discount != null){
            promo.add(discount);
        }
        return discount!=null;
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

    private void charge(double ammount) {
        payment.get(activePayment).charge(ammount);
    }

}
