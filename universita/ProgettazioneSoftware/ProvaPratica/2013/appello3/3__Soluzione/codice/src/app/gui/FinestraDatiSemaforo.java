package app.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class FinestraDatiSemaforo extends JFrame {
  
  private final String[] labels = {"Nome: ", "Durata Giallo (sec): ", "Durata Verde (sec): "};
  
  JTextField nomeSemaforoField = new JTextField(10);
  JTextField durataGialloField = new JTextField(10);
  JTextField durataVerdeField = new JTextField(10);
  
  private final JButton bottoneOK = new JButton("OK");
  
  String nomeSemaforo;
  int durataGiallo;
  int durataVerde;
  
  public FinestraDatiSemaforo() {
    super("Semaforo");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    
    int numPairs = labels.length;
    JTextField[] textFields = {nomeSemaforoField, durataGialloField, durataVerdeField};
    //Create and populate the panel
    JPanel p = new JPanel(new SpringLayout());
    for (int i = 0; i < numPairs; i++) {
        JLabel l = new JLabel(labels[i], JLabel.TRAILING);
        p.add(l);
        l.setLabelFor(textFields[i]);
        p.add(textFields[i]);
    }

    //Lay out the panel
    SpringUtilities.makeCompactGrid(p,
                                    numPairs, 2, //rows, cols
                                    16, 16,        //initX, initY
                                    6, 6);       //xPad, yPad
    
    JPanel pannelloInferiore = new JPanel();
    bottoneOK.addActionListener(new ListenerFinestraDatiSemaforo(this));
    pannelloInferiore.add(bottoneOK);
    
    getContentPane().add(p, BorderLayout.CENTER);
    getContentPane().add(pannelloInferiore, BorderLayout.PAGE_END);
    
    pack();
    setLocationRelativeTo(null);
    setVisible(true);

  }
  
  public String leggiNomeSemaforo() {
    return nomeSemaforo;
  }
  
  public int leggiDurataGiallo() {
    return durataGiallo;
  }
  
  public int leggiDurataVerde() {
    return durataVerde;
  }
  
  
  public void aspettaOK() {
    synchronized (getContentPane()) {
      try {
        getContentPane().wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
        System.exit(1);
      }
    }
  }
  
}
