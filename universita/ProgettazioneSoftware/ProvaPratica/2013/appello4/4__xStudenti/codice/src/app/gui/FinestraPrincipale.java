package app.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FinestraPrincipale extends JFrame {

  public FinestraPrincipale() {
    super("Pub");
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    JPanel pannello = new JPanel();
    pannello.add(new JLabel("Premi per iniziare una nuova sessione"));
    
    JLabel introLabel = new JLabel("  Gestione Ordini  ",JLabel.CENTER);
    introLabel.setForeground(Color.red);
    introLabel.setFont(new Font(introLabel.getFont().getFontName(),Font.BOLD,25));

    JPanel pannelloBottone = new JPanel();
    
    /* DA COMPLETARE A CURA DELLO STUDENTE */
    // Crea bottone, aggiungi listener e metti il bottone nel pannelloBottone
    
    
    
    
    getContentPane().add(introLabel, BorderLayout.PAGE_START);
    getContentPane().add(pannello, BorderLayout.CENTER);
    getContentPane().add(pannelloBottone, BorderLayout.PAGE_END);

    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }
}
