package app.gui.displayAdv;

import javax.swing.*;
import java.awt.*;

class KeyPad extends JPanel {

	JButton unoBtn = new JButton("1");
	JButton dueBtn = new JButton("2");
	JButton treBtn = new JButton("3");
	JButton quattroBtn = new JButton("4");
	JButton cinqueBtn = new JButton("5");
	JButton seiBtn = new JButton("6");
	JButton setteBtn = new JButton("7");
	JButton ottoBtn = new JButton("8");
	JButton noveBtn = new JButton("9");
	JButton mostraBtn = new JButton("Mostra");
	JButton cancBtn = new JButton("C");
	JButton zeroBtn = new JButton("0");

	public KeyPad() {
		this.setLayout(new GridLayout(4, 3));
		this.add(setteBtn);
		this.add(ottoBtn);
		this.add(noveBtn);
		this.add(quattroBtn);
		this.add(cinqueBtn);
		this.add(seiBtn);
		this.add(unoBtn);
		this.add(dueBtn);
		this.add(treBtn);
		this.add(cancBtn);
		this.add(zeroBtn);
		this.add(mostraBtn);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
}
