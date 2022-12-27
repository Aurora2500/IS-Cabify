package es.ulpgc.is.gui;

import javax.swing.*;
import java.awt.*;

public class AppForm extends JFrame {
	private JPanel panel;
	private JPanel historyTab;
	private JPanel rideTab;
	private JButton reserveButton;
	private JPanel reserveTab;
	private JPanel paymentTab;
	private JTextField promoCodeField;
	private JButton promoCodeButton;
	private JButton newPaymentMethodButton;
	private JList paymentList;
	private JButton rateButton;
	private JButton tipButton;
	private JList historyList;
	private JButton cancelButton;
	private JList reservedList;

	public AppForm() throws HeadlessException {
		setContentPane(panel);

		// exit on close
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//  set the callback for the button
		reserveButton.addActionListener(e -> {
			//  do something
			buttonCallback();
		});

		promoCodeButton.addActionListener(e -> {
			promoCodeButtonCallback();
		});

		newPaymentMethodButton.addActionListener(e -> {
			newPaymentMethodButtonCallback();
		});
	}

	private void newPaymentMethodButtonCallback() {
		NewPaymentDialog dialog = new NewPaymentDialog();
		dialog.pack();
		dialog.setVisible(true);
	}

	private void buttonCallback() {
		System.out.println("Button pressed");
	}

	private void promoCodeButtonCallback() {
		// print out the promo code
		System.out.println("Redeeming promo code: " + promoCodeField.getText());
		// clear out the promo code field
		promoCodeField.setText("");
		// TODO: connect this to the model controller
	}
}
