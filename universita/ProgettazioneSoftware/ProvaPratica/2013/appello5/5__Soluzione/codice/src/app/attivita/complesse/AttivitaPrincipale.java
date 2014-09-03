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

    regata = AttivitaIO.inserisciDatiRegata();
    
    equipaggio = AttivitaIO.inserisciDatiEquipaggio();
    
    Executor.perform(new IscriviEquipaggio(regata, equipaggio));
    
    do {
      equipaggio = AttivitaIO.inserisciDatiEquipaggio();
      Executor.perform(new IscriviEquipaggio(regata, equipaggio));
      altroEquipaggio = AttivitaIO.chiediSeAltroEquipaggio();
    } while (altroEquipaggio);

    Executor.perform(new InizializzaSimulazione(regata));
    
    AttivitaIO.visualizzaRegata(regata);

    Executor.perform(new DeterminaVincitori(regata));
    
    AttivitaIO.visualizzaRiassuntoRegata(regata);
  }

  
  public synchronized boolean estEseguita() {
    return eseguita;
  }
}
