package app.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerFinestraDatiCliente implements ActionListener {

  private FinestraDatiCliente finestra;

  public ListenerFinestraDatiCliente(FinestraDatiCliente frame) {
    finestra = frame;
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {

    finestra.nomeCliente = finestra.nomeClienteField.getText();
    finestra.cognomeCliente = finestra.cognomeClienteField.getText();
    finestra.indirizzoCliente = finestra.indirizzoClienteField.getText();

    if (finestra.nomeCliente == null || finestra.nomeCliente.equals("") ||
        finestra.cognomeCliente == null || finestra.cognomeCliente.equals("") ||
        finestra.indirizzoCliente == null || finestra.indirizzoCliente.equals("")) {
      ErrorNotifier.notifyError("Inserire i dati correttamente!");
      return;
    }

    finestra.dispose();
    synchronized (finestra.getContentPane()) {
      finestra.getContentPane().notify();
    }
  }
}
