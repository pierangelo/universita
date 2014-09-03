package app.attivita.complesse;

import app._framework.*;
import app.attivita.AttivitaIO;
import app.attivita.atomiche.*;
import app.dominio.*;

public class AttivitaPrincipale implements Runnable {
  
  private boolean eseguita = false;
  
  private Regata regata;
  private Equipaggio equipaggio;
  private boolean altroEquipaggio;

  public synchronized void run() {
    
    if (eseguita == true)
      return;
    
    eseguita = true;

    
    /* DA COMPLETARE A CURA DELLO STUDENTE */
    
    
    
    
    
  }

  
  public synchronized boolean estEseguita() {
    return eseguita;
  }
}
