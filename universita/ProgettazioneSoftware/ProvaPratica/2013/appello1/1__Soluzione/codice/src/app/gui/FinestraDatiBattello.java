package app.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class FinestraDatiBattello extends JFrame implements ActionListener {
  
  private final String[] labels = {"Nome: ", "Posti: ", "Lunghezza scafo (mt): ", "Profondita scafo (mt): "};
  
  JTextField nomeBattelloField = new JTextField(10);
  JTextField numPostiField = new JTextField(10);
  JTextField lunghezzaField = new JTextField(10);
  JTextField profonditaField = new JTextField(10);
  
  private final JButton bottoneOK = new JButton("OK");
  private final JButton autofillButton = new JButton("Autofill");
  
  String nomeBattello;
  int postiBattello;
  int lunghezzaScafo;
  int profonditaScafo;
  
  public FinestraDatiBattello() {
    super("Battello");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    
    int numPairs = labels.length;
    JTextField[] textFields = {nomeBattelloField, numPostiField, lunghezzaField, profonditaField};
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
    bottoneOK.addActionListener(new ListenerFinestraDatiBattello(this));
    autofillButton.addActionListener(this);
    pannelloInferiore.add(autofillButton);
    pannelloInferiore.add(bottoneOK);
    
    getContentPane().add(p, BorderLayout.CENTER);
    getContentPane().add(pannelloInferiore, BorderLayout.PAGE_END);
    
    pack();
    setLocationRelativeTo(null);
    setVisible(true);

  }
  
  public String leggiNomeBattello() {
    return nomeBattello;
  }
  
  public int leggiPostiBattello() {
    return postiBattello;
  }
  
  public int leggiLunghezzaBattello() {
    return lunghezzaScafo;
  }
  
  public int leggiProfonditaBattello() {
    return profonditaScafo;
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
    //autofill
    nomeBattelloField.setText("Caronte");
    numPostiField.setText("100");
    lunghezzaField.setText("5");
    profonditaField.setText("2");
  }
  
  // Metodi ausiliari
  
  private String getRandomId() {
    Random r = new Random();
    return Long.toString(Math.abs(r.nextLong()), 36).toUpperCase();
  }
  
  private int getRandomInt() {
    Random rand = new Random();
    int min = 1;
    int max = 10;

    return rand.nextInt(max - min + 1) + min;
  }
  
}
