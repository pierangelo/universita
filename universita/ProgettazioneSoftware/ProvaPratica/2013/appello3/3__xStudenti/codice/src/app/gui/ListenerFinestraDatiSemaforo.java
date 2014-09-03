package app.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerFinestraDatiSemaforo implements ActionListener {
  
  private FinestraDatiSemaforo finestra;
  
  public ListenerFinestraDatiSemaforo(FinestraDatiSemaforo frame) {
    finestra = frame;
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    
    String nome = finestra.nomeSemaforoField.getText();
    if (nome == null || nome.equals("")) {
      ErrorNotifier.notifyError("Inserire i dati correttamente!");
      return;
    }
    else {
      finestra.nomeSemaforo = nome;
    }
    
    int durataGiallo = 0;
    int durataVerde = 0;
    try {
      durataGiallo = Integer.parseInt(finestra.durataGialloField.getText());
      durataVerde = Integer.parseInt(finestra.durataVerdeField.getText());
      
      if (durataGiallo <= 0 || durataVerde <= 0) {
        ErrorNotifier.notifyError("Inserire correttamente i dati!");
        return;
      }
    }
    catch(Exception ex) {
      ErrorNotifier.notifyError("Inserire correttamente i dati!");
      return;
    }
    finestra.durataGiallo = durataGiallo;
    finestra.durataVerde = durataVerde;
    
    finestra.dispose();
    
    synchronized (finestra.getContentPane()) {
      finestra.getContentPane().notify();
    }
  }
}
