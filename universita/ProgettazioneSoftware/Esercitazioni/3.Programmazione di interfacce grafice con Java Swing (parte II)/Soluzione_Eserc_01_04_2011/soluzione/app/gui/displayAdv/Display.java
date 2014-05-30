package app.gui.displayAdv;

import java.awt.*;
import javax.swing.*;

/* NOTA: in questa soluzione alternativa il pannello che corrisponde al tastierino numerico
 * e' definito in una classe a sé (KeyPad) che estende JPanel.
 */

public class Display extends JFrame {
	// nota: e' l'unica classe pubblica del package

	final FlowLayout flowLayout = new FlowLayout();
	
	final JPanel pannelloSinistro = new JPanel();
	final BorderLayout borderLayout1 = new BorderLayout();
	final JPasswordField passwordText = new JPasswordField();
	final KeyPad keypad = new KeyPad();
	
	final JPanel pannelloDestro = new JPanel();
	final BorderLayout borderLayout2 = new BorderLayout();
	final JLabel digitCodeLabel = new JLabel("Codice digitato:");
	final JTextArea digitCodeText = new JTextArea(3, 30);
	
	final Listener ascoltatore = new Listener(this);

	public Display() {
		super("Pannello Digitale - Versione 2");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(flowLayout);
		
		// costruzione pannello sinistro
		pannelloSinistro.setLayout(borderLayout1);
		pannelloSinistro.add(passwordText, BorderLayout.NORTH);
		passwordText.setEditable(false);
		pannelloSinistro.add(keypad, BorderLayout.CENTER);
		
		// costruzione pannello destro
		pannelloDestro.setLayout(borderLayout2);
		pannelloDestro.add(digitCodeLabel, BorderLayout.NORTH);
		pannelloDestro.add(digitCodeText, BorderLayout.CENTER);
		digitCodeText.setBorder(BorderFactory.createLineBorder(Color.black));
		digitCodeText.setEditable(false);
		
		this.getContentPane().add(pannelloSinistro, null);
		this.getContentPane().add(pannelloDestro, null);

		/* Assegnazione comandi ed ascoltatori: l'ascoltatore e' lo stesso per tutti i
		 * bottoni; ad ogni bottone numerico associamo un ActionCommand (stringa) che  
		 * corrisponde alla cifra visualizzata (vedi il Listener). 
		 */
		keypad.zeroBtn.addActionListener(ascoltatore);
		keypad.zeroBtn.setActionCommand("0");
		keypad.unoBtn.addActionListener(ascoltatore);
		keypad.unoBtn.setActionCommand("1");
		keypad.dueBtn.addActionListener(ascoltatore);
		keypad.dueBtn.setActionCommand("2");
		keypad.treBtn.addActionListener(ascoltatore);
		keypad.treBtn.setActionCommand("3");
		keypad.quattroBtn.addActionListener(ascoltatore);
		keypad.quattroBtn.setActionCommand("4");
		keypad.cinqueBtn.addActionListener(ascoltatore);
		keypad.cinqueBtn.setActionCommand("5");
		keypad.seiBtn.addActionListener(ascoltatore);
		keypad.seiBtn.setActionCommand("6");
		keypad.setteBtn.addActionListener(ascoltatore);
		keypad.setteBtn.setActionCommand("7");
		keypad.ottoBtn.addActionListener(ascoltatore);
		keypad.ottoBtn.setActionCommand("8");
		keypad.noveBtn.addActionListener(ascoltatore);
		keypad.noveBtn.setActionCommand("9");
		keypad.cancBtn.addActionListener(ascoltatore);
		keypad.cancBtn.setActionCommand(Listener.CANCELLABTN);
		keypad.mostraBtn.addActionListener(ascoltatore);
		keypad.mostraBtn.setActionCommand(Listener.MOSTRABTN);

		// altre impostazioni grafiche
		this.pack();
		/* centra il frame nello schermo
		 * (tecnica alternativa al calcolo della posizione rispetto alle dimensioni dello schermo) */
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}