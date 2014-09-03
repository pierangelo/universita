package app.gui;

import javax.swing.*;

import java.awt.*;

public class FinestraPrincipale extends JFrame {
  
  private final JLabel label = new JLabel("Premi per simulare una nuova gara");

  public FinestraPrincipale() {
    
    super("Simulazione Gara");
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    Container frmContentPane = this.getContentPane();
    
    JPanel pannello = new JPanel();
    pannello.add(label);
    
    JPanel pannelloBottone = new JPanel();
    
    /* DA COMPLETARE A CURA DELLO STUDENTE */
    // Crea bottone, aggiungi listener e aggiungi bottone a pannelloBottone
    
    
    
    
    
    JPanel center = new JPanel();
    center.setBackground(Color.BLUE);
    JLabel label = new JLabel("<html><br>&nbsp;&nbsp;&nbsp;&nbsp;/\\&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;/\\<br>&nbsp;/\\/&nbsp;&nbsp;\\&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;/\\&nbsp;&nbsp;/&nbsp;&nbsp;\\/\\<br>/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\\/\\_/&nbsp;&nbsp;\\/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\\<br><br>&nbsp;&nbsp;&nbsp;O__&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;O__<br>&nbsp;&nbsp;_/`.\\&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;_/`.\\<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`=(&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`=(&nbsp;&nbsp;<br>~~~~~~~~~~~~~~~~~~~~~~~<br><br></html>");
    label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
    label.setForeground(Color.WHITE);
    center.add(label);
    
    frmContentPane.add(pannello, BorderLayout.PAGE_START);
    frmContentPane.add(center, BorderLayout.CENTER);
    frmContentPane.add(pannelloBottone, BorderLayout.PAGE_END);
    
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

}
