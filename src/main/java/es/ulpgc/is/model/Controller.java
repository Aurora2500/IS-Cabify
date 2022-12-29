package es.ulpgc.is.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

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

	public PastTrip pickupNow(String pickupAddress, String destinationAddress) {
		Driver driver = driverRepository.getAvailableDriver();
		LocalDateTime now = LocalDateTime.now();
		PastTrip trip = new PastTrip(pickupAddress, destinationAddress, driver, now.minus(5, ChronoUnit.MINUTES), now);
		pastTripRepository.add(trip);
		return trip;
	}

		public ReservedTrip reserveTrip(String pickupAddress, String destinationAddress, LocalDateTime pickupTime) {
		Driver driver = driverRepository.getAvailableDriver();
		ReservedTrip trip = new ReservedTrip(pickupAddress, destinationAddress, driver, pickupTime);
		reservedTripRepository.add(trip);
		return trip;
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

	public PaymentMethod addPayPal(String name) {
		PayPal payPal = new PayPal(name);
		paymentManager.addPayment(payPal);
		return  payPal;
	}

	public PaymentMethod addBankCard(String IBAN) {
		BankCard bankCard = new BankCard(IBAN);
		paymentManager.addPayment(bankCard);
		return bankCard;
	}

	public void giveTip(double tip, PastTrip trip) {
		paymentManager.PayTip(tip);
		Driver driver = trip.driver();
		trip.giveTip(new Tip(tip, driver));
	}

	public PastTrip finishTrip(int index) {
		ReservedTrip trip = reservedTripRepository.removeReserve(index);
		LocalDateTime now = LocalDateTime.now();
		double price = new Random().nextDouble() + 10;
		PastTrip pastTrip = new PastTrip(trip.pickupAddress(), trip.destinationAddress(), trip.driver(), trip.pickupTime(), now);
		pastTripRepository.add(pastTrip);
		return pastTrip;
	}
}
