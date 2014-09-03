package app.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ListenerFinestraDatiSimulazione implements ActionListener {
  
  private FinestraDatiSimulazione finestra;
  
  public ListenerFinestraDatiSimulazione(FinestraDatiSimulazione frame) {
    finestra = frame;
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    
    String codice = finestra.codiceSimulazioneField.getText();
    if (codice == null || codice.equals("")) {
      ErrorNotifier.notifyError("Inserire i dati correttamente!");
      return;
    }
    else {
      finestra.codiceSimulazione = codice;
    }
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);    
    try {
      finestra.dataSimulazione = sdf.parse(finestra.dataSimulazioneField.getText());
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    finestra.dispose();
    
    synchronized (finestra.getContentPane()) {
      finestra.getContentPane().notify();
    }
  }
}
