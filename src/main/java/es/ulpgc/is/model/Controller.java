package es.ulpgc.is.model;

import java.time.LocalDateTime;

public class Controller {
	private final PaymentManager paymentManager = new PaymentManager();
	private final DriverRepository driverRepository = new DriverRepository();
	private final ReservedTripRepository reservedTripRepository = new ReservedTripRepository();
	private final PastTripRepository pastTripRepository = new PastTripRepository();


	public PaymentManager paymentManager() {
		return paymentManager;
	}

	public ReservedTripRepository reservedTripRepository() {
		return reservedTripRepository;
	}

	public PastTripRepository pastTripRepository() {
		return pastTripRepository;
	}

	public void addDriver(String name, String id) {
		Driver driver = new Driver(name, id);
		driverRepository.add(driver);
	}

	public void addReserveTrip(String pickupAddress, String destinationAddress, String driverId, LocalDateTime pickupTime) {
		Driver driver = driverRepository.getDriver(driverId);
		ReservedTrip trip = new ReservedTrip(pickupAddress, destinationAddress, driver, pickupTime);
		reservedTripRepository.add(trip);
	}

	public void addPastTrip(String pickupAddress, String destinationAddress, String driverId, LocalDateTime departureTime, LocalDateTime arriveTime) {
		Driver driver = driverRepository.getDriver(driverId);
		PastTrip trip = new PastTrip(pickupAddress, destinationAddress, driver, departureTime, arriveTime);
		pastTripRepository.add(trip);
	}

	public void addPayPal(String name) {
		PayPal payPal = new PayPal(name);
		paymentManager.addPayment(payPal);
	}

	public void addBankCard(String IBAN) {
		BankCard bankCard = new BankCard(IBAN);
		paymentManager.addPayment(bankCard);
	}
}
