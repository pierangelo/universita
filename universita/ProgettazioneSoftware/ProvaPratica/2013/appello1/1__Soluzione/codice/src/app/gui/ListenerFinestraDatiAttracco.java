package app.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerFinestraDatiAttracco implements ActionListener {
  
  private FinestraDatiAttracco finestra;
  
  public ListenerFinestraDatiAttracco(FinestraDatiAttracco frame) {
    finestra = frame;
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    
    String nome = finestra.nomeField.getText();
    if (nome == null || nome.equals("")) {
      ErrorNotifier.notifyError("Inserire i dati correttamente!");
      return;
    }
    else {
      finestra.nomeAttracco = nome;
    }
    
    int distanza = 0;
    int lunghezza = 0;
    try {
      distanza = Integer.parseInt((String)(finestra.distanzeList.getSelectedItem()));
      lunghezza = Integer.parseInt((String)(finestra.lunghezzaField.getText()));
      if (distanza <= 0 || lunghezza <= 0) {
        ErrorNotifier.notifyError("Inserire correttamente i dati!");
        return;
      }
    }
    catch(Exception ex) {
      ErrorNotifier.notifyError("Inserire correttamente i dati!");
      return;
    }
    finestra.distanza = distanza;
    finestra.lunghezza = lunghezza;
    
    finestra.dispose();
    synchronized (finestra.getContentPane()) {
      finestra.getContentPane().notify();
    }
  }
}
