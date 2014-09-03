package app.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerFinestraDatiBattello implements ActionListener {
  
  private FinestraDatiBattello finestra;
  
  public ListenerFinestraDatiBattello(FinestraDatiBattello frame) {
    finestra = frame;
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    
    String nome = finestra.nomeBattelloField.getText();
    if (nome == null || nome.equals("")) {
      ErrorNotifier.notifyError("Inserire i dati correttamente!");
      return;
    }
    else {
      finestra.nomeBattello = nome;
    }
    
    int posti = 0;
    int lunghezza = 0;
    int profondita = 0;
    try {
      posti = Integer.parseInt(finestra.numPostiField.getText());
      lunghezza = Integer.parseInt(finestra.lunghezzaField.getText());
      profondita = Integer.parseInt(finestra.profonditaField.getText());
      
      if (posti <= 0 || lunghezza <= 0 || profondita <= 0) {
        ErrorNotifier.notifyError("Inserire correttamente i dati!");
        return;
      }
    }
    catch(Exception ex) {
      ErrorNotifier.notifyError("Inserire correttamente i dati!");
      return;
    }
    finestra.postiBattello = posti;
    finestra.lunghezzaScafo = lunghezza;
    finestra.profonditaScafo = profondita;
    
    finestra.dispose();
    
    synchronized (finestra.getContentPane()) {
      finestra.getContentPane().notify();
    }
  }
}
