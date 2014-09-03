package app.attivita.atomiche;

import app._framework.Executor;
import app._framework.Task;
import app.dominio.EccezionePrecondizioni;
import app.dominio.Tratta;
import app.dominio.ManagerPercorre;
import app.dominio.TipoLinkPercorre;
import app.dominio.Battello;

public class PosizionaBattello implements Task {

  private boolean eseguita = false;
  private Battello battello;
  private Tratta tratta;

  public PosizionaBattello(Battello t, Tratta l) {
    battello = t;
    tratta = l;
  }

  public synchronized void esegui(Executor e) {
    if (e == null || eseguita == true)
      return;
    eseguita = true;
    
    TipoLinkPercorre linkPercorre = null;
    try {
      linkPercorre = new TipoLinkPercorre(battello, tratta, 0);
    } catch (EccezionePrecondizioni ex) {
      ex.printStackTrace();
      System.exit(1);
    }
    ManagerPercorre.inserisci(linkPercorre);
  }

  public synchronized boolean estEseguita() {
    return eseguita;
  }

}
