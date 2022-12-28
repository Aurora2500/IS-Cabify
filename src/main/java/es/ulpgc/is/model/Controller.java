package es.ulpgc.is.model;

public class Controller {

    private PaymentManager paymentManager;
    private DriverRepository driverRepository;
    private ReserveTripRepository reserveTripRepository;
    private PastTripRepository pastTripRepository;

    public Controller() {
        paymentManager = new PaymentManager();
        driverRepository = new DriverRepository();
        pastTripRepository = new PastTripRepository();
        reserveTripRepository = new ReserveTripRepository();
    }

}
