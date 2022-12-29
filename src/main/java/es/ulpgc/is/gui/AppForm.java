package es.ulpgc.is.gui;

import es.ulpgc.is.model.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class AppForm extends JFrame {
	Controller controller;
	private JPanel panel;
	private JButton reserveButton;
	private JTextField promoCodeField;
	private JButton promoCodeButton;
	private JButton newPaymentMethodButton;
	private JList<PaymentMethod> paymentList;
	private JButton rateButton;
	private JButton tipButton;
	private JList<PastTrip> historyList;
	private JButton cancelButton;
	private JList<ReservedTrip> reservedList;
	private JButton removePaymentMethodButton;
	private JButton setActivePaymentButton;
	private JTextField destinationAddressField;
	private JTextField pickupAddressField;
	private JRadioButton reserveRadioButton;
	private JRadioButton pickUpNowRadioButton;
	private JTextField pickupTimeField;
	private JLabel reservedPickupTimeField;
	private JLabel reservedDriverField;
	private JLabel reservedPickupAddressField;
	private JLabel reservedDestinationAddressField;
	private JLabel historyPickupAddressLabel;
	private JLabel historyDestinationAddressLabel;
	private JLabel historyDriverLabel;
	private JLabel historyDepartureTimeLabel;
	private JLabel historyArrivalTimeLabel;
	private JLabel historyRateLabel;
	private JLabel historyTipLabel;
	private JLabel discountLabel;
	private JLabel activePaymentLabel;
	private JButton debugFinishTripButton;

	private DefaultListModel<PastTrip> pastTripListModel = new DefaultListModel<>();
	private DefaultListModel<ReservedTrip> reservedTripListModel = new DefaultListModel<>();
	private DefaultListModel<PaymentMethod> paymentMethodListModel = new DefaultListModel<>();

	public AppForm(Controller controller) throws HeadlessException {
		setContentPane(panel);

		this.controller = controller;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupGetRideTab();
		setupReservedTab();
		setupHistoryTab();
		setupPaymentTab();
	}

	private void setupGetRideTab() {
		ButtonGroup getRideGroup = new ButtonGroup();
		getRideGroup.add(reserveRadioButton);
		getRideGroup.add(pickUpNowRadioButton);
		reserveRadioButton.setSelected(true);

		reserveRadioButton.addActionListener(e -> {
			pickupTimeField.setEnabled(true);
		});

		pickUpNowRadioButton.addActionListener(e -> {
			pickupTimeField.setEnabled(false);
			pickupTimeField.setText("");
		});

		KeyAdapter disableReserveButtonIfEmpty = new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				reserveButton.setEnabled(!pickupAddressField.getText().isEmpty() && !destinationAddressField.getText().isEmpty());
			}
		};

		pickupAddressField.addKeyListener(disableReserveButtonIfEmpty);
		destinationAddressField.addKeyListener(disableReserveButtonIfEmpty);

		reserveButton.addActionListener(e -> {
			if (reserveRadioButton.isSelected()) {
				// get the time from the text field
				String timeString = pickupTimeField.getText();
				try {
					LocalDateTime time = LocalDateTime.parse(timeString);
					ReservedTrip trip = controller.reserveTrip(pickupAddressField.getText(), destinationAddressField.getText(), time);
					reservedTripListModel.addElement(trip);
				} catch (DateTimeParseException ex) {
					JOptionPane.showMessageDialog(this, "Invalid time format. Please use the format yyyy-MM-dd HH:mm");
					return;
				}
			} else {
				PastTrip trip = controller.pickupNow(pickupAddressField.getText(), destinationAddressField.getText());
				pastTripListModel.addElement(trip);
			}
		});
	}

	private void setupReservedTab() {
		List<ReservedTrip> trips = controller.reservedTripRepository().list();

		for (ReservedTrip trip : trips) {
			reservedTripListModel.addElement(trip);
		}

		reservedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		reservedList.setModel(reservedTripListModel);

		reservedList.addListSelectionListener(e -> {
			ReservedTrip trip = reservedList.getSelectedValue();
			if (trip != null) {
				reservedPickupTimeField.setText(trip.pickupTime().truncatedTo(ChronoUnit.SECONDS).toString());
				reservedDriverField.setText(trip.driver().name());
				reservedPickupAddressField.setText(trip.pickupAddress());
				reservedDestinationAddressField.setText(trip.destinationAddress());
			}
		});

		cancelButton.addActionListener(e -> {
			int index = reservedList.getSelectedIndex();
			if (index != -1) {
				System.out.println("Canceling trip " + index);
				trips.remove(index);
				reservedTripListModel.remove(index);
			}
		});

		debugFinishTripButton.addActionListener(e -> {
			int index = reservedList.getSelectedIndex();
			if (index == -1) {
				return;
			}
			System.out.println("Finishing trip " + index);
			PastTrip pastTrip = controller.finishTrip(index);
			reservedTripListModel.remove(index);
			pastTripListModel.addElement(pastTrip);

		});
	}

	private void setupHistoryTab() {
		List<PastTrip> trips = controller.pastTripRepository().list();

		for(PastTrip trip: trips) {
			pastTripListModel.addElement(trip);
		}

		historyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		historyList.setModel(pastTripListModel);

		historyList.addListSelectionListener(e -> {
			PastTrip trip = historyList.getSelectedValue();
			if (trip == null) {
				return;
			}
			historyPickupAddressLabel.setText(trip.pickupAddress());
			historyDestinationAddressLabel.setText(trip.destinationAddress());
			historyDriverLabel.setText(trip.driver().name());
			historyDepartureTimeLabel.setText(trip.departureTime().truncatedTo(ChronoUnit.SECONDS).toString());
			historyArrivalTimeLabel.setText(trip.arrivalTime().truncatedTo(ChronoUnit.SECONDS).toString());
			Optional<Rating> rating = trip.rating();
			historyRateLabel.setText(rating.map(value -> value.rating() + "/10").orElse("---"));
			Optional<Tip> tip = trip.tip();
			historyTipLabel.setText(tip.map(value -> String.format("%.2f€", value.tip())).orElse("---"));
		});

		rateButton.addActionListener(e -> {
			int index = historyList.getSelectedIndex();
			if (index == -1) {
				return;
			}
			PastTrip trip = trips.get(index);
			if (trip.rating().isPresent()) {
				JOptionPane.showMessageDialog(this, "You have already rated this trip");
				return;
			}
			RateTripDialog dialog = new RateTripDialog(trip);
			dialog.pack();
			dialog.setVisible(true);
		});

		tipButton.addActionListener(e -> {
			int index = historyList.getSelectedIndex();
			if (index == -1) {
				return;
			}
			PastTrip trip = trips.get(index);
			if (trip.tip().isPresent()) {
				JOptionPane.showMessageDialog(this, "You have already tipped this trip");
				return;
			}
			TipDialog dialog = new TipDialog(controller, trip);
			dialog.pack();
			dialog.setVisible(true);
		});
	}

	private void setupPaymentTab() {
		for(PaymentMethod pm: controller.paymentManager().getPayments()) {
			paymentMethodListModel.addElement(pm);
		}

		paymentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		paymentList.setModel(paymentMethodListModel);

		PaymentMethod activePayment = controller.paymentManager().activePayment();
		activePaymentLabel.setText(String.valueOf(activePayment));

		newPaymentMethodButton.addActionListener(e -> {
			newPaymentMethodButtonCallback();
		});

		promoCodeButton.addActionListener(e -> {
			promoCodeButtonCallback();
		});

		setActivePaymentButton.addActionListener(e -> {
			setActivePaymentButtonCallback();
		});

		removePaymentMethodButton.addActionListener(e -> {
			removePaymentMethodButtonCallback();
		});
	}

	private void setActivePaymentButtonCallback() {
		int index = paymentList.getSelectedIndex();
		if (index == -1) {
			return;
		}
		PaymentMethod payment = paymentMethodListModel.get(index);
		controller.paymentManager().setActivePayment(index);
		activePaymentLabel.setText(String.valueOf(payment));
	}

	private void removePaymentMethodButtonCallback() {
		int index = paymentList.getSelectedIndex();
		if (index == -1) {
			return;
		}
		PaymentMethod pm = paymentMethodListModel.get(index);
		controller.paymentManager().removePayment(pm);
		paymentMethodListModel.remove(index);
	}

	private void newPaymentMethodButtonCallback() {
		NewPaymentDialog dialog = new NewPaymentDialog(controller, pm -> paymentMethodListModel.addElement(pm));
		dialog.pack();
		dialog.setVisible(true);
	}

	private void buttonCallback() {
		System.out.println("Button pressed");
	}

	private void promoCodeButtonCallback() {
		String code = promoCodeField.getText();
		if (code.isEmpty()) {
			// ignore if empty
			return;
		}
		// print out the promo code
		System.out.println("Redeeming promo code: " + promoCodeField.getText());
		// clear out the promo code field
		promoCodeField.setText("");

		boolean validCode = controller.paymentManager().redeemCode(code);
		if (!validCode) {
			JOptionPane.showMessageDialog(this, "Invalid promo code");
			return;
		}
		// valid code, update discount label
		double discount = controller.paymentManager().getDiscount();
		discountLabel.setText(String.format("%.0f%%", discount * 100));
	}
}
