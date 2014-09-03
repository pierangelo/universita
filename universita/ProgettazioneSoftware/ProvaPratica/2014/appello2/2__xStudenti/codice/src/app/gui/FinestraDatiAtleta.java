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
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import app.dominio.Atleta;

public class FinestraDatiAtleta extends JDialog implements ActionListener{

	private String nome;
	private String cognome;
	private String nazione;

	private final String[] labels = {"Nome: ", "Cognome: ", "Nazione: "};

	private final JTextField nomeTxt = new JTextField(10);
	private final JTextField cognomeTxt = new JTextField(10);
	private final JComboBox nazioneCombo = new JComboBox(Atleta.naz);

	private final JPanel sudPnl = new JPanel();
	private final JButton okBtn = new JButton("OK");
	private final JButton autofillBtn = new JButton("Autofill");


	public FinestraDatiAtleta() {
		super((JFrame)null, "Dati Atleta", true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		inizializza();
	}

	private void inizializza() {

		int numPairs = labels.length-1;
		JTextField[] textFields = {nomeTxt, cognomeTxt};
		//Create and populate the panel
		JPanel p = new JPanel(new SpringLayout());
		for (int i = 0; i < numPairs; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			p.add(l);
			l.setLabelFor(textFields[i]);
			p.add(textFields[i]);
		}
		
		JLabel l = new JLabel(labels[2], JLabel.TRAILING);
		p.add(l);
		l.setLabelFor(nazioneCombo);
		p.add(nazioneCombo);

		//Lay out the panel
		SpringUtilities.makeCompactGrid(p,
				numPairs+1, 2, //rows, cols
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

	public String leggiNome() {
		return nome;
	}

	public String leggiCognome() {
		return cognome;
	}

	public String leggiNazione() {
		return nazione;
	}

	public void actionPerformed(ActionEvent ev) {

		String command = ev.getActionCommand();

		if (command.equals("OK")) {

			nome = nomeTxt.getText();
			cognome = cognomeTxt.getText();
			nazione = (String) nazioneCombo.getSelectedItem();

			if (nome == null || nome.equals("") ||
					cognome == null || cognome.equals("")) {
				ErrorNotifier.notifyError("Inserire i dati correttamente!");
			}
			else {

				this.setVisible(false);
				this.dispose();

			}
		}
		else {
			Atleta a = Atleta.getRandomAtleta();
			nomeTxt.setText(a.getNome());
			cognomeTxt.setText(a.getCognome());
			nazioneCombo.setSelectedItem(a.getNazione());
		}

	}

}
