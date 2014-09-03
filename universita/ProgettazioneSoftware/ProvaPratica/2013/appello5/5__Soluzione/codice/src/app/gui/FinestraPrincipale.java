package app.gui;

import javax.swing.*;

import java.awt.*;

public class FinestraPrincipale extends JFrame {
  
  private final JLabel label = new JLabel("Premi per simulare una nuova regata");

  public FinestraPrincipale() {
    
    super("Simulazione Regata");
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    Container frmContentPane = this.getContentPane();
    
    JPanel pannello = new JPanel();
    pannello.add(label);
    
    JPanel pannelloBottone = new JPanel();
    
    JButton button = new JButton("Nuova Regata");
    button.addActionListener(new ListenerFinestraPrincipale(this));
    pannelloBottone.add(button);
    
    JPanel center = new JPanel();
    center.setBackground(Color.BLUE);
    JLabel label = new JLabel("<html> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D&nbsp;&nbsp;&nbsp;D&nbsp;&nbsp;&nbsp;D&nbsp;&nbsp;&nbsp;D<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;/<br> &nbsp;&lt;+&lt;=O=O=O=O=O=O=O=O=O=&gt;+===<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\\&nbsp;&nbsp;&nbsp;\\&nbsp;&nbsp;&nbsp;\\&nbsp;&nbsp;&nbsp;\\<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D&nbsp;&nbsp;&nbsp;D&nbsp;&nbsp;&nbsp;D&nbsp;&nbsp;&nbsp;D</html>");
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
