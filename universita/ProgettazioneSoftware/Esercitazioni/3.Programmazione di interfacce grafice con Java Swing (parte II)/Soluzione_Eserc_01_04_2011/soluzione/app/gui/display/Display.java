package app.gui.display;

import java.awt.*;
import javax.swing.*;

public class Display extends JFrame {
	// nota: e' l'unica classe pubblica del package

	final FlowLayout flowLayout = new FlowLayout();
	
	final JPanel pannelloSinistro = new JPanel();
	final BorderLayout borderLayout1 = new BorderLayout();
	final JPasswordField passwordText = new JPasswordField();
	final JPanel keypad = new JPanel();
	
	final JButton unoBtn = new JButton("1");
	final JButton dueBtn = new JButton("2");
	final JButton treBtn = new JButton("3");
	final JButton quattroBtn = new JButton("4");
	final JButton cinqueBtn = new JButton("5");
	final JButton seiBtn = new JButton("6");
	final JButton setteBtn = new JButton("7");
	final JButton ottoBtn = new JButton("8");
	final JButton noveBtn = new JButton("9");
	final JButton mostraBtn = new JButton("Mostra");
	final JButton cancBtn = new JButton("C");
	final JButton zeroBtn = new JButton("0");
	
	final JPanel pannelloDestro = new JPanel();
	final BorderLayout borderLayout2 = new BorderLayout();
	final JLabel digitCodeLabel = new JLabel("Codice digitato:");
	final JTextArea digitCodeText = new JTextArea(3, 30);
	
	final Listener ascoltatore = new Listener(this);

	public Display() {
		super("Pannello Digitale");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(flowLayout);
		
		// costruzione pannello sinistro
		pannelloSinistro.setLayout(borderLayout1);
		pannelloSinistro.add(passwordText, BorderLayout.NORTH);
		pannelloSinistro.add(keypad, BorderLayout.CENTER);
		passwordText.setEditable(false);
		keypad.setLayout(new GridLayout(4, 3));
		keypad.add(setteBtn);
		keypad.add(ottoBtn);
		keypad.add(noveBtn);
		keypad.add(quattroBtn);
		keypad.add(cinqueBtn);
		keypad.add(seiBtn);
		keypad.add(unoBtn);
		keypad.add(dueBtn);
		keypad.add(treBtn);
		keypad.add(cancBtn);
		keypad.add(zeroBtn);
		keypad.add(mostraBtn);
		keypad.setBorder(BorderFactory.createLineBorder(Color.black));
		
		// costruzione pannello destro
		pannelloDestro.setLayout(borderLayout2);
		pannelloDestro.add(digitCodeLabel, BorderLayout.NORTH);
		pannelloDestro.add(digitCodeText, BorderLayout.CENTER);
		digitCodeText.setBorder(BorderFactory.createLineBorder(Color.black));
		digitCodeText.setEditable(false);
		
		// aggiunta pannelli in contentPane
		this.getContentPane().add(pannelloSinistro, null);
		this.getContentPane().add(pannelloDestro, null);

		/* Assegnazione comandi ed ascoltatori: l'ascoltatore e' lo stesso per tutti i
		 * bottoni; ad ogni bottone numerico associamo un ActionCommand (stringa) che  
		 * corrisponde alla cifra visualizzata (vedi il Listener). 
		 */
		zeroBtn.addActionListener(ascoltatore);
		zeroBtn.setActionCommand("0");
		unoBtn.addActionListener(ascoltatore);
		unoBtn.setActionCommand("1");
		dueBtn.addActionListener(ascoltatore);
		dueBtn.setActionCommand("2");
		treBtn.addActionListener(ascoltatore);
		treBtn.setActionCommand("3");
		quattroBtn.addActionListener(ascoltatore);
		quattroBtn.setActionCommand("4");
		cinqueBtn.addActionListener(ascoltatore);
		cinqueBtn.setActionCommand("5");
		seiBtn.addActionListener(ascoltatore);
		seiBtn.setActionCommand("6");
		setteBtn.addActionListener(ascoltatore);
		setteBtn.setActionCommand("7");
		ottoBtn.addActionListener(ascoltatore);
		ottoBtn.setActionCommand("8");
		noveBtn.addActionListener(ascoltatore);
		noveBtn.setActionCommand("9");
		cancBtn.addActionListener(ascoltatore);
		cancBtn.setActionCommand(Listener.CANCELLABTN);
		mostraBtn.addActionListener(ascoltatore);
		mostraBtn.setActionCommand(Listener.MOSTRABTN);

		// altre impostazioni grafiche
		this.pack();
		/* centra il frame nello schermo
		 * (tecnica alternativa al calcolo della posizione rispetto alle dimensioni dello schermo) */
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}