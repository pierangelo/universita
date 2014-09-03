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
    
    TipoLinkComprende link = null;
    try {
      link = new TipoLinkComprende(simulazione, semaforo);
    } catch (EccezionePrecondizioni ex) {
      // TODO Auto-generated catch block
      ex.printStackTrace();
      System.exit(1);
    }
    ManagerComprende.inserisci(link);
  }

  public synchronized boolean estEseguita() {
    return eseguita;
  }

}
