package app.attivita.atomiche;

import app._framework.Executor;
import app._framework.Task;
import app.dominio.EccezionePrecondizioni;
import app.dominio.ManagerComprende;
import app.dominio.Semaforo;
import app.dominio.Simulazione;
import app.dominio.TipoLinkComprende;

public class AggiungiSemaforo implements Task {

  private boolean eseguita = false;
  
  private Simulazione simulazione;
  private Semaforo semaforo;

  public AggiungiSemaforo(Simulazione sim, Semaforo sem) {
    simulazione = sim;
    semaforo = sem;
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

}
