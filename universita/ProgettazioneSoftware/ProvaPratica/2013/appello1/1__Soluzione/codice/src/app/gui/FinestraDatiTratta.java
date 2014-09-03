package app.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class FinestraDatiTratta extends JFrame implements ActionListener {
  
  private final String[] labels = {"Nome: ", "Profondita min (mt): ", "Profondita max (mt): "};
  
  JTextField nomeTrattaField = new JTextField(10);
  JTextField minProfonditaField = new JTextField(10);
  JTextField maxProfonditaField = new JTextField(10);
  
  private final JButton bottoneOK = new JButton("OK");
  private final JButton autofillButton = new JButton("Autofill");
  
  String nomeTratta;
  int minProfondita;
  int maxProfondita;
  
  public FinestraDatiTratta() {
    super("Tratta fluviale");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    
    int numPairs = labels.length;
    JTextField[] textFields = {nomeTrattaField, minProfonditaField, maxProfonditaField};
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
    bottoneOK.addActionListener(new ListenerFinestraDatiTratta(this));
    autofillButton.addActionListener(this);
    pannelloInferiore.add(autofillButton);
    pannelloInferiore.add(bottoneOK);
    
    getContentPane().add(p, BorderLayout.CENTER);
    getContentPane().add(pannelloInferiore, BorderLayout.PAGE_END);
    
    pack();
    setLocationRelativeTo(null);
    setVisible(true);

  }
  
  public String leggiNomeTratta() {
    return nomeTratta;
  }
  
  public int leggiMinProfondita() {
    return minProfondita;
  }
  
  public int leggiMaxProfondita() {
    return maxProfondita;
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

  @Override
  public void actionPerformed(ActionEvent arg0) {
    nomeTrattaField.setText("Tevere");
    minProfonditaField.setText("5");
    maxProfonditaField.setText("10");
  }
  
}
