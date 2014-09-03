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

import app.dominio.Cliente;

public class FinestraDatiCliente extends JFrame implements ActionListener {
  
  private final String[] labels = {"Nome: ", "Cognome: ", "Indirizzo: "};
  
  JTextField nomeClienteField = new JTextField(10);
  JTextField cognomeClienteField = new JTextField(10);
  JTextField indirizzoClienteField = new JTextField(15);
  
  private final JButton bottoneOK = new JButton("OK");
  private final JButton autofillButton = new JButton("Autofill");
  
  String nomeCliente;
  String cognomeCliente;
  String indirizzoCliente;
  
  public FinestraDatiCliente() {
    super("Cliente");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    
    int numPairs = labels.length;
    JTextField[] textFields = {nomeClienteField, cognomeClienteField, indirizzoClienteField};
    
    //Create and populate the panel
    JPanel p = new JPanel(new SpringLayout());
    for (int i = 0; i < numPairs; i++) {
        JLabel l = new JLabel(labels[i], JLabel.TRAILING);
        p.add(l);
        //JTextField textField = new JTextField(10);
        l.setLabelFor(textFields[i]);
        p.add(textFields[i]);
    }

    //Lay out the panel
    SpringUtilities.makeCompactGrid(p,
                                    numPairs, 2, //rows, cols
                                    16, 16,        //initX, initY
                                    6, 6);       //xPad, yPad
    
    JPanel pannelloInferiore = new JPanel();
    bottoneOK.addActionListener(new ListenerFinestraDatiCliente(this));
    autofillButton.addActionListener(this);
    pannelloInferiore.add(autofillButton);
    pannelloInferiore.add(bottoneOK);
    
    getContentPane().add(p, BorderLayout.CENTER);
    getContentPane().add(pannelloInferiore, BorderLayout.PAGE_END);
    
    pack();
    setLocationRelativeTo(null);
    setVisible(true);

  }
  
  public String leggiNomeCliente() {
    return nomeCliente;
  }
  
  public String leggiCognomeCliente() {
    return cognomeCliente;
  }
  
  public String leggiIndirizzoCliente() {
    return indirizzoCliente;
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
  public void actionPerformed(ActionEvent e) {
    Cliente cliente = Cliente.getClienteDefault();
    nomeClienteField.setText(cliente.getNome());
    cognomeClienteField.setText(cliente.getCognome());
    indirizzoClienteField.setText(cliente.getIndirizzo());
  }

}
