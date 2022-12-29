package es.ulpgc.is.gui;

import es.ulpgc.is.model.PastTrip;
import es.ulpgc.is.model.Rating;

import javax.swing.*;
import java.awt.event.*;

public class RateTripDialog extends JDialog {
	PastTrip trip;
	private JPanel contentPane;
	private JButton buttonOK;
	private JButton buttonCancel;
	private JSlider ratingSlider;
	private JTextArea ratingContent;

	public RateTripDialog(PastTrip trip) {
		this.trip = trip;
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);

		buttonOK.addActionListener(e -> onOK());

		buttonCancel.addActionListener(e -> onCancel());

		// call onCancel() when cross is clicked
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onCancel();
			}
		});

		// call onCancel() on ESCAPE
		contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}

	private void onOK() {
		trip.setRating(new Rating(ratingSlider.getValue(), ratingContent.getText()));
		dispose();
	}

	private void onCancel() {
		// add your code here if necessary
		dispose();
	}
}
