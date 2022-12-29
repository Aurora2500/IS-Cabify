package es.ulpgc.is.gui;

import es.ulpgc.is.model.Controller;
import es.ulpgc.is.model.PastTrip;
import es.ulpgc.is.model.PaymentMethod;
import es.ulpgc.is.model.ReservedTrip;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.*;
import java.awt.*;

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
					controller.reserveTrip(pickupAddressField.getText(), destinationAddressField.getText(), time);
				} catch (DateTimeParseException ex) {
					JOptionPane.showMessageDialog(this, "Invalid time format. Please use the format yyyy-MM-dd HH:mm");
					return;
				}
			} else {
				controller.pickupNow(pickupAddressField.getText(), destinationAddressField.getText());
			}
		});
	}

	private void setupReservedTab() {
		List<ReservedTrip> trips = controller.reservedTripRepository().list();

		ListModel<ReservedTrip> model = new AbstractListModel<ReservedTrip>() {
			@Override
			public int getSize() {
				return trips.size();
			}
			@Override
			public ReservedTrip getElementAt(int i) {
				return trips.get(i);
			}
		};
		reservedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		reservedList.setModel(model);

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
			}
		});
	}

	private void setupHistoryTab() {
		List<PastTrip> trips = controller.pastTripRepository().list();
		ListModel<PastTrip> model = new AbstractListModel<>() {
			@Override
			public int getSize() {
				return trips.size();
			}
			@Override
			public PastTrip getElementAt(int i) {
				return trips.get(i);
			}
		};

		historyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		historyList.setModel(model);

		historyList.addListSelectionListener(e -> {
			PastTrip trip = historyList.getSelectedValue();
			if (trip != null) {
			}
		});

		rateButton.addActionListener(e -> {
			RateTripDialog dialog = new RateTripDialog();
			dialog.pack();
			dialog.setVisible(true);
		});
	}

	private void setupPaymentTab() {
		ListModel<PaymentMethod> model = new AbstractListModel<>() {
			@Override
			public int getSize() {
				return controller.paymentManager().getPayments().size();
			}
			@Override
			public PaymentMethod getElementAt(int i) {
				return controller.paymentManager().getPayments().get(i);
			}
		};

		paymentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		paymentList.setModel(model);

		newPaymentMethodButton.addActionListener(e -> {
			newPaymentMethodButtonCallback();
		});
	}

	private void newPaymentMethodButtonCallback() {
		NewPaymentDialog dialog = new NewPaymentDialog(controller);
		dialog.pack();
		dialog.setVisible(true);
	}

	private void buttonCallback() {
		System.out.println("Button pressed");
	}

	private void promoCodeButtonCallback() {
		if (promoCodeField.getText().isEmpty()) {
			// ignore if empty
			return;
		}
		// print out the promo code
		System.out.println("Redeeming promo code: " + promoCodeField.getText());
		// clear out the promo code field
		promoCodeField.setText("");
	}
}
