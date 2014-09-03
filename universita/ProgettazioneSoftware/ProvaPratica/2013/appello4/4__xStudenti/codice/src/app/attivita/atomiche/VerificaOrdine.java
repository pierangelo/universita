package app.attivita.atomiche;

import java.util.Iterator;
import java.util.Set;

import app._framework.Executor;
import app._framework.Task;
import app.dominio.Bevanda;
import app.dominio.EccezioneMoltMinMax;
import app.dominio.Ordine;
import app.dominio.Snack;
import app.dominio.Prodotto;
import app.dominio.Tavolo;
import app.dominio.TipoLinkContiene;

public class VerificaOrdine implements Task {

  private boolean eseguita = false;

  private Ordine ordine;
  private Tavolo tavolo;
  private boolean result;


  public VerificaOrdine(Ordine o, Tavolo t) {
    this.ordine = o;
    this.tavolo = t;
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