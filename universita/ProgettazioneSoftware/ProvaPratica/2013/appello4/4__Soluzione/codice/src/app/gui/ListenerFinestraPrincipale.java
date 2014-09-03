package app.gui;

import java.awt.event.*;
import app.attivita.complesse.AttivitaPrincipale;

public class ListenerFinestraPrincipale implements ActionListener {
  
  private FinestraPrincipale finestra;
  
  public ListenerFinestraPrincipale(FinestraPrincipale frame) {
    finestra = frame;
  }

  public void actionPerformed(ActionEvent arg0) {
    AttivitaPrincipale attivitaPrincipale = new AttivitaPrincipale();
    Thread t = new Thread(attivitaPrincipale);
    t.start();
    finestra.dispose();
  }
}
