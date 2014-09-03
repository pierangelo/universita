package app.gui;

import java.awt.event.*;

import app.attivita.complesse.*;

class ListenerFinestraPrincipale implements ActionListener {
  
  private FinestraPrincipale frame;
  
  public ListenerFinestraPrincipale(FinestraPrincipale fin) {
    frame = fin;
  }
  
  public void actionPerformed(ActionEvent evento) {
    Thread threadAttivitaPrincipale = new Thread(new AttivitaPrincipale());
    threadAttivitaPrincipale.start();
    frame.dispose();
  }
}
