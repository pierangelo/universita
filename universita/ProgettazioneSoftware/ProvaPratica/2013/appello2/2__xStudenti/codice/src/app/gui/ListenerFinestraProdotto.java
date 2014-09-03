package app.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerFinestraProdotto implements ActionListener {

  private FinestraProdotto finestra;

  public ListenerFinestraProdotto(FinestraProdotto frame) {
    finestra = frame;
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    
    int quantita = 0;
    
    try {
      quantita =  Integer.parseInt(finestra.fieldQuantita.getText());
      if (quantita == 0) {
        ErrorNotifier.notifyError("Inserire correttamente la quantita'");
        return;
      }
    }
    catch (Exception e) {
      ErrorNotifier.notifyError("Inserire correttamente la quantita'");
      return;
    }
    
    
    finestra.quantita = quantita;

    finestra.dispose();
    synchronized (finestra.getContentPane()) {
      finestra.getContentPane().notify();
    }
  }
}
