package app.attivita.atomiche;

import app._framework.Executor;
import app._framework.Task;
import app.dominio.Cliente;
import app.dominio.Ordine;

public class RegistraOrdine implements Task {

  private boolean eseguita = false;
  
  private Ordine ordine;
  private Cliente cliente;

 
  public RegistraOrdine(Ordine ordine, Cliente cliente) {
    this.ordine = ordine;
    this.cliente = cliente;
  }

  public synchronized void esegui(Executor e) {

    if (e == null || eseguita == true)
      return;
    eseguita = true;
        
    // Crea link
    ordine.inserisciLinkEseguitoDa(cliente);
    
  }

  public synchronized boolean estEseguita() {
    return eseguita;
  }

}