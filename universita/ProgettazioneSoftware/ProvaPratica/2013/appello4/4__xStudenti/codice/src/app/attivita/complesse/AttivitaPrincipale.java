package app.attivita.complesse;

import app._framework.Executor;
import app.attivita.AttivitaIO;
import app.attivita.RecordSelezioneProdotto;
import app.attivita.atomiche.AggiungiProdotto;
import app.attivita.atomiche.CreaOrdine;
import app.attivita.atomiche.RegistraOrdine;
import app.attivita.atomiche.VerificaOrdine;
import app.dominio.Tavolo;
import app.dominio.Ordine;

public class AttivitaPrincipale implements Runnable {

  private boolean eseguita = false;

  private Tavolo tavolo;
  private Ordine ordine;
  private RecordSelezioneProdotto recordProdotto;
  private boolean altroProdotto;
  private boolean ordineValido;
  private boolean nuovoOrdine;


  public AttivitaPrincipale() {}

  public synchronized void run() {
    if (eseguita == true)
      return;
    eseguita = true;

    do {

      tavolo = AttivitaIO.selezionaTavolo();
      
      CreaOrdine co = new CreaOrdine();
      Executor.perform(co);
      ordine = co.getRisultato();

      /* DA COMPLETARE A CURA DELLO STUDENTE */
      
      
      
      
      nuovoOrdine = AttivitaIO.chiediSeNuovoOrdine();

    } while (nuovoOrdine);

  }

  public synchronized boolean estEseguita() {
    return eseguita;
  }
}
