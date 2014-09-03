package app.attivita.atomiche;

import app._framework.*;
import app.dominio.*;

public class IscriviEquipaggio implements Task {
  
  private boolean eseguita = false;
  private Regata regata;
  private Equipaggio equipaggio;

  public IscriviEquipaggio(Regata regata, Equipaggio equipaggio) {
    this.regata = regata;
    this.equipaggio = equipaggio;
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
}
