package app.attivita.atomiche;

import app._framework.Executor;
import app._framework.Task;
import app.dominio.Battello;
import app.dominio.Tratta;

public class VerificaNavigabilita implements Task {

  private boolean eseguita = false;
  private Battello battello;
  private Tratta tratta;
  private boolean result;

  public VerificaNavigabilita(Battello b, Tratta tr) {
    battello = b;
    tratta = tr;
  }

  public synchronized void esegui(Executor e) {
    if (e == null || eseguita == true)
      return;
    eseguita = true;
    
    /* DA COMPLETARE A CURA DELLO STUDENTE */
    
    
    
    
  }

  public synchronized boolean estEseguita() {
    return eseguita;
  }
  
  public synchronized boolean getRisultato() {
    return result;
  }

}
