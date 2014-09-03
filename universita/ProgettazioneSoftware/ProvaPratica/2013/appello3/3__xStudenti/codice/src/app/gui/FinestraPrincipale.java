package app.gui;

import javax.swing.*;

import java.awt.*;

public class FinestraPrincipale extends JFrame {
  
  private final JLabel label = new JLabel("Premi per iniziare una nuova simulazione");

  public FinestraPrincipale() {
    
    super("Simulazione semafori");
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    Container frmContentPane = this.getContentPane();
    JPanel pannello = new JPanel();
    pannello.add(label);
    
    JPanel pannelloBottone = new JPanel();
    
    /* DA COMPLETARE A CURA DELLO STUDENTE */
    // Crea bottone, aggiungi listener e aggiungi bottone a pannelloBottone

    
    
    
    frmContentPane.add(pannello, BorderLayout.PAGE_START);
    frmContentPane.add(pannelloBottone, BorderLayout.PAGE_END);
    
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

}
