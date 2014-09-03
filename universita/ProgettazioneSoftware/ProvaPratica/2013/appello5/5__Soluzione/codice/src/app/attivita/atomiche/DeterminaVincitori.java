package app.attivita.atomiche;

import java.util.*;

import app._framework.*;
import app.dominio.*;

public class DeterminaVincitori implements Task {
  private boolean eseguita = false;
  private Regata regata;

  public DeterminaVincitori(Regata regata) {
    this.regata = regata;
  }

  public synchronized void esegui(Executor e) {
    if (e == null || eseguita == true)
      return;
    eseguita = true;

    Iterator<TipoLinkPartecipa> it = null;
    try {
      it = regata.getLinkPartecipa().iterator();
    } catch (EccezioneMoltMinMax eccezione) {
      eccezione.printStackTrace();
      System.exit(1);
    }

    float massimoCorrente = 0;
    Set<Equipaggio> vincitoriCorrenti = new HashSet<Equipaggio>();

    while (it.hasNext()) {
      TipoLinkPartecipa linkCorrente = it.next();
      if (linkCorrente.getKmPercorsi() > massimoCorrente) {// trovato un
                                                           // equipaggio migliore
                                                           // dei precedenti
        // svuota l'insieme corrente dei vincitori
        vincitoriCorrenti.clear();
        // Aggiorna il massimo corrente
        massimoCorrente = linkCorrente.getKmPercorsi();
      }
      if (linkCorrente.getKmPercorsi() >= massimoCorrente) {
        // inserisci l'equipaggio corrente
        // NOTA: Se > l'insieme vincitoriCorrenti e' stato svuotato
        vincitoriCorrenti.add(linkCorrente.getEquipaggio());
      }
    }
    // Aggiorna i vincitori della regata
    Iterator<Equipaggio> itc = vincitoriCorrenti.iterator();

    while (itc.hasNext()) {
      regata.inserisciLinkVincitore(itc.next());
    }
  }

  public synchronized boolean estEseguita() {
    return eseguita;
  }

}
