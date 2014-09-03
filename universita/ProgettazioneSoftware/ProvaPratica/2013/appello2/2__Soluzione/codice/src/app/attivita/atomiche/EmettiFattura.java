package app.attivita.atomiche;

import java.util.Random;

import app._framework.Executor;
import app._framework.Task;
import app.dominio.EccezionePrecondizioni;
import app.dominio.Fattura;
import app.dominio.ManagerRelativaA;
import app.dominio.Ordine;
import app.dominio.TipoLinkRelativaA;

public class EmettiFattura implements Task {

  private boolean eseguita = false;
  
  private Ordine ordine;
  private Fattura result;

 
  public EmettiFattura(Ordine ordine) {
    this.ordine = ordine;
  }

  public synchronized void esegui(Executor e) {

    if (e == null || eseguita == true)
      return;
    eseguita = true;
    
    // Crea fattura
    result = new Fattura(getRandomId());
    
    // Crea link
    TipoLinkRelativaA link;
    try {
      link = new TipoLinkRelativaA(result, ordine);
      ManagerRelativaA.inserisci(link);
    } catch (EccezionePrecondizioni e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
      System.exit(-1);
    }
  }

  public synchronized boolean estEseguita() {
    return eseguita;
  }
  
  public synchronized Fattura getRisultato() {
    return result;
  }
  
  
  //Metodi ausiliari
  
  private String getRandomId() {
    Random r = new Random();
    return Long.toString(Math.abs(r.nextLong()), 36).toUpperCase();
  }

}