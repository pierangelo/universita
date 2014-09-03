package app.attivita.atomiche;

import java.util.*;

import app._framework.*;
import app.dominio.*;

public class DeterminaVincitori implements Task {
	
  private boolean eseguita = false;
  private Gara gara;

  public DeterminaVincitori(Gara gara) {
    this.gara = gara;
  }

  public synchronized void esegui(Executor e) {
    if (e == null || eseguita == true)
      return;
    eseguita = true;

    Iterator<TipoLinkPartecipa> it = null;
    try {
      it = gara.getLinkPartecipa().iterator();
    } catch (EccezioneMoltMinMax eccezione) {
      eccezione.printStackTrace();
      System.exit(1);
    }

    double massimoCorrente = 0;
    Set<Atleta> vincitoriCorrenti = new HashSet<Atleta>();

    while (it.hasNext()) {
      TipoLinkPartecipa linkCorrente = it.next();
      if (linkCorrente.getMtPercorsi() > massimoCorrente) {// trovato un
                                                           // atleta migliore
                                                           // dei precedenti
        // svuota l'insieme corrente dei vincitori
        vincitoriCorrenti.clear();
        // Aggiorna il massimo corrente
        massimoCorrente = linkCorrente.getMtPercorsi();
      }
      if (linkCorrente.getMtPercorsi() >= massimoCorrente) {
        // inserisci l'atleta corrente
        // NOTA: Se > l'insieme vincitoriCorrenti e' stato svuotato
        vincitoriCorrenti.add(linkCorrente.getAtleta());
      }
    }
    // Aggiorna i vincitori della gara
    Iterator<Atleta> itc = vincitoriCorrenti.iterator();

    while (itc.hasNext()) {
      try {
		gara.inserisciLinkVince(new TipoLinkVince(gara, itc.next()));
	} catch (EccezionePrecondizioni e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    }
  }

  public synchronized boolean estEseguita() {
    return eseguita;
  }

}
