package app.attivita.complesse;

import app._framework.*;
import app._gestioneeventi.*;
import app.attivita.AttivitaIO;
import app.attivita.RecordDatiAttracco;
import app.attivita.atomiche.*;
import app.dominio.*;

public class AttivitaPrincipale implements Runnable {

  private boolean eseguita = false;

  // Variabili processo
  private Tratta tratta;
  private Battello battello;
  private boolean navigabile;
  private RecordDatiAttracco recordAttracco;
  private boolean altroAttracco;
  private boolean altraSimulazione;

  public synchronized void run() {

    if (eseguita == true)
      return;
    eseguita = true;

    // Inizio Processo
    do {
      tratta = AttivitaIO.inserisciDatiTratta();

      battello = AttivitaIO.inserisciDatiBattello();

      VerificaNavigabilita vn = new VerificaNavigabilita(battello, tratta);
      Executor.perform(vn);
      navigabile = vn.getRisultato();

      if (navigabile) {

        PosizionaBattello pt = new PosizionaBattello(battello, tratta);
        Executor.perform(pt);

        do {
          recordAttracco = AttivitaIO.inserisciDatiAttracco();

          AggiungiAttracco af = new AggiungiAttracco(tratta, recordAttracco);
          Executor.perform(af);

          altroAttracco = AttivitaIO.chiediSeAltroAttracco();

        } while(altroAttracco);

        InizializzaSimulazione is = new InizializzaSimulazione(battello);
        Executor.perform(is);

        AttivitaIO.visualizzaSimulazione(battello);

        EsecuzioneEnvironment.disattivaListener();

        altraSimulazione = AttivitaIO.chiediSeAltraSimulazione();
      }
      else {
        AttivitaIO.mostraErrore("Il fiume non e' abbastanza profondo per il battello!");
      }

    } while(altraSimulazione);


  }

  public synchronized boolean estEseguita() {
    return eseguita;
  }
}
