package app.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class FinestraDatiTavola extends JDialog implements ActionListener {

	private String marca;

	private final String[] labels = {"Marca: "};

	private final JComboBox marcaComboBox = new JComboBox(new String[]{"SuperTavola", "SpeedBoard", "SnowMaster"});

	private final JPanel sudPnl = new JPanel();
	private final JButton okBtn = new JButton("OK");
	//  private final JButton autofillBtn = new JButton("Autofill");


	public FinestraDatiTavola() {
		super((JFrame)null, "Dati Tavola", true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		inizializza();
	}

	private void inizializza() {

		int numPairs = labels.length;

		JPanel p = new JPanel(new SpringLayout());

		JLabel marcaLbl = new JLabel(labels[0], JLabel.TRAILING);
		p.add(marcaLbl);
		marcaLbl.setLabelFor(marcaComboBox);
		p.add(marcaComboBox);

		//Lay out the panel
		SpringUtilities.makeCompactGrid(p,
				numPairs, 2, //rows, cols
				16, 16,        //initX, initY
				6, 6);       //xPad, yPad

		okBtn.addActionListener(this);
		okBtn.setActionCommand("OK");
		sudPnl.add(okBtn);

		getContentPane().add(p, BorderLayout.CENTER);
		getContentPane().add(sudPnl, BorderLayout.SOUTH);
	}

	public void visualizza() {
		pack();
		setLocationRelativeTo(null);
		// bloccante fino al setVisible(false) e dispose
		setVisible(true);
	}

	public String leggiMarca() {
		return marca;
	}

	public void actionPerformed(ActionEvent ev) {

		String command = ev.getActionCommand();

		if (command.equals("OK")) {
			marca = (String) marcaComboBox.getSelectedItem();
			this.setVisible(false);
			this.dispose();
		}
	}

}