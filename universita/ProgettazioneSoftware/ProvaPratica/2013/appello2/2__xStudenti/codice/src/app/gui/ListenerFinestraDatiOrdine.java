package app.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerFinestraDatiOrdine implements ActionListener {

  private FinestraDatiOrdine finestra;

  public ListenerFinestraDatiOrdine(FinestraDatiOrdine frame) {
    finestra = frame;
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {

    finestra.idOrdine = finestra.idOrdineField.getText();
    finestra.aDomicilio = finestra.aDomicilioBox.isSelected();

    finestra.dispose();
    synchronized (finestra.getContentPane()) {
      finestra.getContentPane().notify();
    }
  }
}
