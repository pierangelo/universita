package app.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.*;

public class FinestraDatiRegata extends JFrame implements ActionListener {

  private String nomeRegata;
  private float distanzaRegata;

  private final String[] labels = {"Nome della regata: ", "Distanza (km): "};

  private final JTextField nomeTxt = new JTextField(10);
  private final JTextField distanzaTxt = new JTextField(10);

  private final JPanel sudPnl = new JPanel();
  private final JButton okBtn = new JButton("OK");
  private final JButton autofillBtn = new JButton("Autofill");


  public FinestraDatiRegata() {
    super("Inserimento Dati Regata");
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    inizializza();
  }

  private void inizializza() {

    int numPairs = labels.length;
    JTextField[] textFields = {nomeTxt, distanzaTxt};
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

    okBtn.addActionListener(this);
    okBtn.setActionCommand("OK");
    sudPnl.add(okBtn);

    autofillBtn.addActionListener(this);
    autofillBtn.setActionCommand("FILL");
    sudPnl.add(autofillBtn);

    getContentPane().add(p, BorderLayout.CENTER);
    getContentPane().add(sudPnl, BorderLayout.SOUTH);


    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public String leggiNome() {
    return nomeRegata;
  }

  public float leggiDistanza() {
    return distanzaRegata;
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

  public void actionPerformed(ActionEvent ev) {

    String command = ev.getActionCommand();

    if (command.equals("OK")) {

      if (nomeTxt.getText() == null || nomeTxt.getText().equals("")) {
        ErrorNotifier.notifyError("Inserire i dati correttamente!");
      }
      else {
        try {
          nomeRegata = nomeTxt.getText();
          distanzaRegata = Float.parseFloat(distanzaTxt.getText());
          if (distanzaRegata > 0) {
            synchronized (getContentPane()) {
              getContentPane().notify();
              dispose();
            }
          }
          else
            ErrorNotifier.notifyError("Inserire i dati correttamente!");
        }
        catch (Exception e) {
          ErrorNotifier.notifyError("Inserire i dati correttamente!");
        }
      }
    }
    else {
      nomeTxt.setText("Olimpiadi");
      distanzaTxt.setText("2");
    }
  }
}
