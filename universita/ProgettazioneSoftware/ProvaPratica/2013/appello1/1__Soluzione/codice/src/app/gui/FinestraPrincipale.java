package app.gui;

import javax.swing.*;

import java.awt.*;

public class FinestraPrincipale extends JFrame {
  
  private final JLabel label = new JLabel("Premi per iniziare una nuova simulazione");

  public FinestraPrincipale() {
    
    super("Simulazione crociera fluviale");
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    Container frmContentPane = this.getContentPane();
    JPanel pannello = new JPanel();
    pannello.add(label);
    
    JPanel center = new JPanel();
    JLabel label = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;_~&nbsp;&nbsp;_~<br>&nbsp;&nbsp;&nbsp;__|=|_|=|__<br>&nbsp;&nbsp;&nbsp;\\&nbsp;o.o.o.o&nbsp;/<br>&nbsp;&nbsp;&nbsp;&nbsp;\\_______/<br>&nbsp;&nbsp;~~~~~~~~~~~~~&nbsp;&nbsp;</html>");
    label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
    label.setForeground(Color.BLUE);
    center.add(label);
    
    JPanel pannelloBottone = new JPanel();
    JButton nuovaSimulazione = new JButton("Nuova Simulazione");
    nuovaSimulazione.addActionListener(new ListenerFinestraPrincipale(this));
    pannelloBottone.add(nuovaSimulazione);
    
    frmContentPane.add(pannello, BorderLayout.PAGE_START);
    frmContentPane.add(center, BorderLayout.CENTER);
    frmContentPane.add(pannelloBottone, BorderLayout.PAGE_END);
    
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

}
