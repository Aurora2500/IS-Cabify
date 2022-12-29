package es.ulpgc.is.gui;

import es.ulpgc.is.model.Controller;
import es.ulpgc.is.model.PastTrip;

import javax.swing.*;
import java.awt.event.*;

public class TipDialog extends JDialog {
	private Controller controller;
	private PastTrip trip;
	private JPanel contentPane;
	private JButton buttonOK;
	private JButton buttonCancel;
	private JTextField tipAmmountField;

	public TipDialog(Controller controller, PastTrip trip) {
		this.controller = controller;
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
		if(tipAmmountField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter a tip amount", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			double tipAmmount = Double.parseDouble(tipAmmountField.getText());
			controller.giveTip(tipAmmount, trip);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Please enter a valid tip amount", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// add your code here
		dispose();
	}

	private void onCancel() {
		// add your code here if necessary
		dispose();
	}
}
