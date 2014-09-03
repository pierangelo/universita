package app.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class FinestraDatiAttracco extends JFrame {
  
  private final String[] labels = {"Nome: ", "Distanza da precedente (mt): ", "Lunghezza banchina (mt): "};
  private final String[] distanze = { "1000", "2000", "3000", "4000", "5000"};
  
  JTextField nomeField = new JTextField(10);
  JComboBox distanzeList = new JComboBox(distanze);
  JTextField lunghezzaField = new JTextField(10);
  
  private final JButton bottoneOK = new JButton("OK");
  
  String nomeAttracco;
  int distanza;
  int lunghezza;
  
  public FinestraDatiAttracco() {
    super("Attracco");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    int numPairs = labels.length;
    
    JPanel p = new JPanel(new SpringLayout());
    
    JLabel l1 = new JLabel(labels[0], JLabel.TRAILING);
    p.add(l1);
    l1.setLabelFor(nomeField);
    p.add(nomeField);
    
    JLabel l2 = new JLabel(labels[1], JLabel.TRAILING);
    p.add(l2);
    l2.setLabelFor(distanzeList);
    p.add(distanzeList);
    
    JLabel l3 = new JLabel(labels[2], JLabel.TRAILING);
    p.add(l3);
    l3.setLabelFor(lunghezzaField);
    p.add(lunghezzaField);
    

    // Lay out the panel
    SpringUtilities.makeCompactGrid(p,
                                    numPairs, 2, //rows, cols
                                    16, 16,        //initX, initY
                                    6, 6);       //xPad, yPad
    
    JPanel pannelloInferiore = new JPanel();
    bottoneOK.addActionListener(new ListenerFinestraDatiAttracco(this));
    pannelloInferiore.add(bottoneOK);
    
    getContentPane().add(p, BorderLayout.CENTER);
    getContentPane().add(pannelloInferiore, BorderLayout.PAGE_END);
    
    pack();
    setLocationRelativeTo(null);
    setVisible(true);

  }
  
  public String leggiNomeAttracco() {
    return nomeAttracco;
  }
  
  public int leggiDistanza() {
    return distanza;
  }
  
  public int leggiLunghezza() {
    return lunghezza;
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
