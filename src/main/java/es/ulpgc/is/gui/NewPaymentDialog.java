package es.ulpgc.is.gui;

import es.ulpgc.is.model.Controller;

import javax.swing.*;
import java.awt.event.*;

public class NewPaymentDialog extends JDialog {
	Controller controller;
	private JPanel contentPane;
	private JButton buttonOK;
	private JButton buttonCancel;
	private JRadioButton PaypalRadioButton;
	private JRadioButton BankRadioButton;
	private JTextField detailsField;
	private JLabel newPaymentMethodField;

	public NewPaymentDialog(Controller controller) {
		this.controller = controller;

		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);

		// group the radio buttons
		ButtonGroup group = new ButtonGroup();
		group.add(PaypalRadioButton);
		group.add(BankRadioButton);


		PaypalRadioButton.addActionListener(e -> newPaymentMethodField.setText("Name:"));

		BankRadioButton.addActionListener(e -> newPaymentMethodField.setText("IBAN:"));

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
		// add your code here
		// print which radio button is selected
		if (PaypalRadioButton.isSelected()) {
			controller.addPayPal(detailsField.getText());
		} else if (BankRadioButton.isSelected()) {
			controller.addBankCard(detailsField.getText());
		}
		dispose();
	}

	private void onCancel() {
		dispose();
	}
}
