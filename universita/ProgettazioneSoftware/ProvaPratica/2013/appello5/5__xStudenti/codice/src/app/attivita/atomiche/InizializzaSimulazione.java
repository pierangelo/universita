package app.attivita.atomiche;

import app._framework.Executor;
import app._framework.Task;
import app._gestioneeventi.EsecuzioneEnvironment;

import app.dominio.EccezioneMoltMinMax;
import app.dominio.Regata;
import app.dominio.TipoLinkPartecipa;

public class InizializzaSimulazione implements Task {

  private boolean eseguita = false;
  private Regata regata;

  public InizializzaSimulazione(Regata regata) {
    this.regata = regata;
  }

  public synchronized void esegui(Executor e) {
    if (e == null || eseguita == true)
      return;
    eseguita = true;

    // Inizializza environment
    try {
      for (TipoLinkPartecipa link : regata.getLinkPartecipa()) {
        EsecuzioneEnvironment.addListener(link.getEquipaggio());
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
