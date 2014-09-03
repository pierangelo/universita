package app.attivita.atomiche;

import app._framework.Executor;
import app._framework.Task;
import app.dominio.Tavolo;
import app.dominio.Ordine;

public class RegistraOrdine implements Task {

  private boolean eseguita = false;
  
  private Ordine ordine;
  private Tavolo cliente;

 
  public RegistraOrdine(Ordine ordine, Tavolo tavolo) {
    this.ordine = ordine;
    this.cliente = tavolo;
  }

  public synchronized void esegui(Executor e) {

    if (e == null || eseguita == true)
      return;
    eseguita = true;
        
    // Crea link
    ordine.inserisciLinkRelativoA(cliente);
    
  }

  public synchronized boolean estEseguita() {
    return eseguita;
  }

}