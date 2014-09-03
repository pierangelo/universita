package app.attivita.atomiche;

import java.util.Iterator;
import java.util.Set;

import app._framework.Executor;
import app._framework.Task;
import app.dominio.EccezioneMoltMinMax;
import app.dominio.Ordine;
import app.dominio.Pizza;
import app.dominio.Prodotto;
import app.dominio.TipoLinkContiene;

public class VerificaOrdine implements Task {

  private boolean eseguita = false;

  private Ordine ordine;
  private boolean result;


  public VerificaOrdine(Ordine o) {
    this.ordine = o;
  }

  public synchronized void esegui(Executor e) {

    if (e == null || eseguita == true)
      return;
    eseguita = true;

    if (ordine.quantiProdotti() >= 2) {
      try {
        Set<TipoLinkContiene> prodotti = ordine.getLinkContiene();
        Iterator<TipoLinkContiene> it = prodotti.iterator();
        TipoLinkContiene link;
        Prodotto prodotto;
        boolean contienePizza = false;
        boolean contieneBibita = false;
        

        while(it.hasNext() && !result) {
          link = it.next();
          prodotto = link.getProdotto();
          if(prodotto.getClass().equals(Pizza.class)) {
            contienePizza = true;
          }
          else {
            contieneBibita = true;
          }
          result = contienePizza && contieneBibita;
        }

      } catch (EccezioneMoltMinMax e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }

  }

  public synchronized boolean estEseguita() {
    return eseguita;
  }

  public synchronized boolean getRisultato() {
    return result;
  }

}