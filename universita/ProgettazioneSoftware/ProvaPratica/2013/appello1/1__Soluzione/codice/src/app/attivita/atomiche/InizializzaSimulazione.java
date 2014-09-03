package app.attivita.atomiche;

import app._framework.Executor;
import app._framework.Task;
import app._gestioneeventi.EsecuzioneEnvironment;
import app.dominio.Battello;

public class InizializzaSimulazione implements Task {

  private boolean eseguita = false;
  private Battello battello;

  public InizializzaSimulazione(Battello bat) {
    battello = bat;
  }

  public synchronized void esegui(Executor e) {
    if (e == null || eseguita == true)
      return;
    eseguita = true;
    
    // Inizializza environment
    EsecuzioneEnvironment.addListener(battello);
    EsecuzioneEnvironment.attivaListener();
    
  }

  public synchronized boolean estEseguita() {
    return eseguita;
  }

}
