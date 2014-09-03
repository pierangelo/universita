package app.attivita.atomiche;

import app._framework.Executor;
import app._framework.Task;
import app.attivita.RecordSelezioneProdotto;
import app.dominio.Ordine;
import app.dominio.TipoLinkContiene;

public class AggiungiProdotto implements Task {

  private boolean eseguita = false;
  
  private Ordine ordine;
  private RecordSelezioneProdotto rsp;

 
  public AggiungiProdotto(Ordine ordine, RecordSelezioneProdotto rsp) {
    this.ordine = ordine;
    this.rsp = rsp;
  }

  public synchronized void esegui(Executor e) {

    if (e == null || eseguita == true)
      return;
    eseguita = true;
    
    
    // Crea link
    TipoLinkContiene link = null;
    try {
      link = new TipoLinkContiene(ordine, rsp.getProdotto(), rsp.getQuantita());
      ordine.inserisciLinkContiene(link);
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
      System.exit(-1);
    }
    
    
  }

  public synchronized boolean estEseguita() {
    return eseguita;
  }

}