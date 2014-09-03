package app.gui;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class FinestraDatiOrdine extends JFrame {
  
  private final String[] labels = {"Id: ", "Data: "};
  
  JTextField idOrdineField = new JTextField(10);
  JTextField dataOrdineField = new JTextField(10);
  JCheckBox aDomicilioBox = new JCheckBox();
  
  private final JButton bottoneOK = new JButton("OK");
  
  String idOrdine;
  Date dataOrdine;
  boolean aDomicilio;
  
  public FinestraDatiOrdine() {
    super("Ordine");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    idOrdineField.setText(getRandomId());
    idOrdineField.setEditable(false);
    
    Calendar today = Calendar.getInstance();
    today.clear(Calendar.HOUR); today.clear(Calendar.MINUTE); today.clear(Calendar.SECOND);
    dataOrdine = today.getTime();
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
    dataOrdineField.setText(sdf.format(dataOrdine));
    dataOrdineField.setEditable(false);
    
    int numPairs = labels.length;
    JTextField[] textFields = {idOrdineField, dataOrdineField};
    
    //Create and populate the panel
    JPanel p = new JPanel(new SpringLayout());
    for (int i = 0; i < numPairs; i++) {
        JLabel l = new JLabel(labels[i], JLabel.TRAILING);
        p.add(l);
        //JTextField textField = new JTextField(10);
        l.setLabelFor(textFields[i]);
        p.add(textFields[i]);
    }
    JLabel lab = new JLabel("Consegna a domicilio", JLabel.TRAILING);
    p.add(lab);
    lab.setLabelFor(aDomicilioBox);
    p.add(aDomicilioBox);

    //Lay out the panel
    SpringUtilities.makeCompactGrid(p,
                                    3, 2, //rows, cols
                                    16, 16,        //initX, initY
                                    6, 6);       //xPad, yPad
    
    JPanel pannelloInferiore = new JPanel();
    bottoneOK.addActionListener(new ListenerFinestraDatiOrdine(this));
    pannelloInferiore.add(bottoneOK);
    
    getContentPane().add(p, BorderLayout.CENTER);
    getContentPane().add(pannelloInferiore, BorderLayout.PAGE_END);
    
    pack();
    setLocationRelativeTo(null);
    setVisible(true);

  }
  
  public String leggiIdOrdine() {
    return idOrdine;
  }
  
  public Date leggiDataOrdine() {
    return dataOrdine;
  }
  
  public boolean leggiConsegnaAdomicilio() {
    return aDomicilio;
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
  
  private String getRandomId() {
    Random r = new Random();
    return Long.toString(Math.abs(r.nextLong()), 36).toUpperCase();
  }

}
