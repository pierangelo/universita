package app.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class FinestraDatiPercorso extends JDialog implements ActionListener {

	private double lunghezza;
	private double altezza;

	private final String[] labels = {"Lunghezza (mt): ", "Altezza (mt): "};

	private final JTextField lunghezzaTxt = new JTextField(10);
	private final JTextField altezzaTxt = new JTextField(10);

	private final JPanel sudPnl = new JPanel();
	private final JButton okBtn = new JButton("OK");
	private final JButton autofillBtn = new JButton("Autofill");


	public FinestraDatiPercorso() {
		super((JFrame)null, "Dati Percorso", true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		inizializza();
	}

	private void inizializza() {

		int numPairs = labels.length;
		JTextField[] textFields = {lunghezzaTxt, altezzaTxt};
		//Create and populate the panel
		JPanel p = new JPanel(new SpringLayout());
		for (int i = 0; i < numPairs; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			p.add(l);
			l.setLabelFor(textFields[i]);
			p.add(textFields[i]);
		}

		//Lay out the panel
		SpringUtilities.makeCompactGrid(p,
				numPairs, 2, //rows, cols
				16, 16,        //initX, initY
				6, 6);       //xPad, yPad

		okBtn.addActionListener(this);
		okBtn.setActionCommand("OK");
		sudPnl.add(okBtn);
		
		autofillBtn.addActionListener(this);
		sudPnl.add(autofillBtn);

		getContentPane().add(p, BorderLayout.CENTER);
		getContentPane().add(sudPnl, BorderLayout.SOUTH);
	}
	
	public void visualizza() {
		pack();
		setLocationRelativeTo(null);
		// bloccante fino al setVisible(false) e dispose
		setVisible(true);
	}

	public double leggiLunghezza() {
		return lunghezza;
	}

	public double leggiAltezza() {
		return altezza;
	}


	public void actionPerformed(ActionEvent ev) {

		String command = ev.getActionCommand();

		if (command.equals("OK")) {

			if (lunghezzaTxt.getText() == null || lunghezzaTxt.getText().equals("")) {
				ErrorNotifier.notifyError("Inserire i dati correttamente!");
			}
			else {
				try {
					lunghezza = Double.parseDouble(lunghezzaTxt.getText());
					altezza = Double.parseDouble(altezzaTxt.getText());
					if (lunghezza > 0 && altezza > 0) {
						this.setVisible(false);
						this.dispose();
					}
					else
						ErrorNotifier.notifyError("Inserire i dati correttamente!");
				}
				catch (Exception e) {
					ErrorNotifier.notifyError("Inserire i dati correttamente!");
				}
			}
		}
		else {
			lunghezzaTxt.setText("30");
			altezzaTxt.setText("6");
		}
		
	}
}
