package app.attivita.atomiche;

import java.util.List;

import app._framework.Executor;
import app._framework.Task;
import app._gestioneeventi.EsecuzioneEnvironment;
import app.dominio.EccezioneMoltMinMax;
import app.dominio.Simulazione;
import app.dominio.TipoLinkComprende;

public class InizializzaSimulazione implements Task {

  private boolean eseguita = false;
  private Simulazione simulazione;

  public InizializzaSimulazione(Simulazione sim) {
    simulazione = sim;
  }

  public synchronized void esegui(Executor e) {
    if (e == null || eseguita == true)
      return;
    eseguita = true;
    
    // Inizializza environment
    List<TipoLinkComprende> links = null;
    try {
      links = simulazione.getLinkComprende();
      for (TipoLinkComprende link : links) {
        EsecuzioneEnvironment.addListener(link.getSemaforo());
      }
    } catch (EccezioneMoltMinMax e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
    EsecuzioneEnvironment.attivaListener();
    
  }

  public synchronized boolean estEseguita() {
    return eseguita;
  }

}
