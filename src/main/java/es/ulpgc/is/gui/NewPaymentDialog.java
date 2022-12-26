package es.ulpgc.is.gui;

import javax.swing.*;
import java.awt.event.*;

public class NewPaymentDialog extends JDialog {
	private JPanel contentPane;
	private JButton buttonOK;
	private JButton buttonCancel;
	private JRadioButton PaypalRadioButton;
	private JRadioButton BankRadioButton;

	public NewPaymentDialog() {
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);

		// group the radio buttons
		ButtonGroup group = new ButtonGroup();
		group.add(PaypalRadioButton);
		group.add(BankRadioButton);

		// set the default selection
		PaypalRadioButton.setSelected(true);

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
			System.out.println("Paypal selected");
		} else if (BankRadioButton.isSelected()) {
			System.out.println("Bank selected");
		}
		dispose();
	}

	private void onCancel() {
		dispose();
	}

	public static void main(String[] args) {
		NewPaymentDialog dialog = new NewPaymentDialog();
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}
}
