package app.attivita.atomiche;

import app._framework.Executor;
import app._framework.Task;
import app.dominio.Simulazione;

public class VerificaSimulazione implements Task {

  private boolean eseguita = false;

  private Simulazione simulazione;
  private boolean result;

  public VerificaSimulazione(Simulazione sim) {
    simulazione = sim;
  }

  public synchronized void esegui(Executor e) {
    if (e == null || eseguita == true)
      return;
    eseguita = true;

    result = simulazione.quantiSemafori() > 1;

  }

  public synchronized boolean estEseguita() {
    return eseguita;
  }

  public synchronized boolean getRisultato() {
    return result;
  }

}
