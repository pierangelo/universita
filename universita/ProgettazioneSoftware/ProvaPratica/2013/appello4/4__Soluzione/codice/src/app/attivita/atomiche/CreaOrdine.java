package app.attivita.atomiche;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import app._framework.Executor;
import app._framework.Task;
import app.dominio.EccezionePrecondizioni;
import app.dominio.Fattura;
import app.dominio.ManagerEmessaPer;
import app.dominio.Ordine;
import app.dominio.TipoLinkEmessaPer;

public class CreaOrdine implements Task {

  private boolean eseguita = false;
  
  private Ordine result;
 
  public CreaOrdine() {
  }

  public synchronized void esegui(Executor e) {

    if (e == null || eseguita == true)
      return;
    eseguita = true;
    
    // Crea fattura
    result = new Ordine(getRandomId(), Calendar.getInstance().getTime());
  }

  public synchronized boolean estEseguita() {
    return eseguita;
  }
  
  public synchronized Ordine getRisultato() {
    return result;
  }
  
  
  //Metodi ausiliari
  
  private String getRandomId() {
    Random r = new Random();
    return Long.toString(Math.abs(r.nextLong()), 36).toUpperCase();
  }

}