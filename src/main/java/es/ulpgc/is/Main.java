package es.ulpgc.is;

import es.ulpgc.is.model.Controller;
import es.ulpgc.is.gui.AppForm;

public class Main {
	public static void main(String[] args) {
		AppForm app = new AppForm();
		app.setSize(1200, 1200);
		app.pack();
		app.setVisible(true);

		Controller controller = new Controller();
	}
}