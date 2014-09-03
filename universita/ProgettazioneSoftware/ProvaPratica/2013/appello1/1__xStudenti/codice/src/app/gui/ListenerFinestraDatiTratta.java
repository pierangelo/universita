package app.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerFinestraDatiTratta implements ActionListener {
  
  private FinestraDatiTratta finestra;
  
  public ListenerFinestraDatiTratta(FinestraDatiTratta frame) {
    finestra = frame;
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    
    String nome = finestra.nomeTrattaField.getText();
    if (nome == null || nome.equals("")) {
      ErrorNotifier.notifyError("Inserire i dati correttamente!");
      return;
    }
    else {
      finestra.nomeTratta = nome;
    }
    
    int minProf = 0;
    int maxProf = 0;
    
    try {
      minProf = Integer.parseInt(finestra.minProfonditaField.getText());
      maxProf = Integer.parseInt(finestra.maxProfonditaField.getText());
      
      if (minProf <= 0 || maxProf <= 0 || minProf > maxProf) {
        ErrorNotifier.notifyError("Inserire correttamente i dati!");
        return;
      }
    }
    catch(Exception ex) {
      ErrorNotifier.notifyError("Inserire correttamente i dati!");
      return;
    }
    finestra.minProfondita = minProf;
    finestra.maxProfondita = maxProf;
    
    finestra.dispose();
    synchronized (finestra.getContentPane()) {
      finestra.getContentPane().notify();
    }
  }
}
