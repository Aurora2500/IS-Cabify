package es.ulpgc.is;

import es.ulpgc.is.model.Controller;
import es.ulpgc.is.gui.AppForm;

import java.time.LocalDateTime;

public class Main {
	public static void main(String[] args) {
		Controller controller = new Controller();

		initializeModel(controller);

		AppForm app = new AppForm(controller);
		app.setSize(1200, 1200);
		app.pack();
		app.setVisible(true);
	}

	private static void initializeModel(Controller controller) {
		LocalDateTime now = LocalDateTime.now();

		controller.addDriver("John", "123");
		controller.addDriver("Jane", "456");
		controller.addDriver("Jack", "789");

		controller.addReserveTrip("Calle 1", "Calle 2", "123", now.plusMinutes(5));
		controller.addReserveTrip("Calle 3", "Calle 4", "456", now.plusMinutes(25));

		controller.addPastTrip("Calle 5", "Calle 6", "789", now.minusMinutes(5), now.minusMinutes(1));
		controller.addPastTrip("Calle 7", "Calle 8", "123", now.minusMinutes(25), now.minusMinutes(20));

		controller.addPayPal("Alice");
		controller.addBankCard("ES1234567890123456789012");
	}
}