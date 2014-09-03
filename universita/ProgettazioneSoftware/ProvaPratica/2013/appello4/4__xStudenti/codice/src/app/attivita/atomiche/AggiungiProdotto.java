package app.attivita.atomiche;

import java.util.Iterator;
import java.util.Set;

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
    int quantita = 0;
    try {
      TipoLinkContiene temp = new TipoLinkContiene(ordine, rsp.getProdotto(), 0);
      TipoLinkContiene currLink = null;

      if (ordine.quantiProdotti() > 0) {
        Set<TipoLinkContiene> linkContiene = ordine.getLinkContiene();
        Iterator<TipoLinkContiene> it = linkContiene.iterator();

        boolean found = false;
        
        while (it.hasNext() && !found) {
          currLink = it.next();
          if(currLink.equals(temp))
            found = true;
        }
        
        if (found) {
          quantita = currLink.getQuantita();
          ordine.eliminaLinkContiene(currLink);
        }
      }

      

      link = new TipoLinkContiene(ordine, rsp.getProdotto(), rsp.getQuantita()+quantita);
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