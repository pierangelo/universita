package app.attivita.complesse;

import app._framework.*;
import app.attivita.AttivitaIO;
import app.attivita.atomiche.*;
import app.dominio.*;

public class AttivitaPrincipale implements Runnable {

  private boolean eseguita = false;

  // Variabili processo
  private Simulazione simulazione;
  private Semaforo semaforo;
  private boolean altroSemaforo;
  private boolean simulazioneValida;
  private boolean altraSimulazione;

  public synchronized void run() {

    if (eseguita == true)
      return;
    eseguita = true;

    // Inizio Processo
    do {

      /* DA COMPLETARE A CURA DELLO STUDENTE */
      
      
      

      if (simulazioneValida) {

        InizializzaSimulazione is = new InizializzaSimulazione(simulazione);
        Executor.perform(is);

        AttivitaIO.visualizzaSimulazione(simulazione);

        altraSimulazione = AttivitaIO.chiediSeAltraSimulazione();
      }
      else {
        AttivitaIO.mostraErrore("Una simulazione deve contenere almeno due semafori!");
      }

    } while(altraSimulazione);

  }

  public synchronized boolean estEseguita() {
    return eseguita;
  }

}
