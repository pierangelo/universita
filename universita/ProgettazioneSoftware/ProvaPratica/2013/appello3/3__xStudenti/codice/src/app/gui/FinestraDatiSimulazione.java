package app.gui;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class FinestraDatiSimulazione extends JFrame {
  
  private final String[] labels = {"Codice: ", "Data: "};
  
  JTextField codiceSimulazioneField = new JTextField(10);
  JTextField dataSimulazioneField = new JTextField(10);
  
  private final JButton bottoneOK = new JButton("OK");
  
  String codiceSimulazione;
  Date dataSimulazione;
  
  public FinestraDatiSimulazione() {
    super("Simulazione");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    dataSimulazioneField.setText(getCurrentDate());
    dataSimulazioneField.setEditable(false);
    
    int numPairs = labels.length;
    JTextField[] textFields = {codiceSimulazioneField, dataSimulazioneField};
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
    bottoneOK.addActionListener(new ListenerFinestraDatiSimulazione(this));
    pannelloInferiore.add(bottoneOK);
    
    getContentPane().add(p, BorderLayout.CENTER);
    getContentPane().add(pannelloInferiore, BorderLayout.PAGE_END);
    
    pack();
    setLocationRelativeTo(null);
    setVisible(true);

  }
  
  public String leggiCodiceSimulazione() {
    return codiceSimulazione;
  }
  
  public Date leggiDataSimulazione() {
    return dataSimulazione;
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
  
  // Metodi ausiliari
  
  private String getCurrentDate() {
    Calendar today = Calendar.getInstance();
    today.clear(Calendar.HOUR);
    today.clear(Calendar.MINUTE);
    today.clear(Calendar.SECOND);
    Date data = today.getTime();
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
    return sdf.format(data);
    
  }
  
}
