package app.dominio;

import java.util.List;

import app._framework.Executor;
import app._framework.Task;
import app._gestioneeventi.Environment;
import app._gestioneeventi.Evento;
import app.dominio.Battello.Stato;
import app.dominio.eventi.Partenza;
import app.dominio.eventi.Percorri;

public class BattelloFired implements Task {

  private boolean eseguita = false;
  private Battello battello;
  private Evento evento;

  public BattelloFired(Battello battello, Evento evento) {
    this.battello = battello;
    this.evento = evento;
  }

  public synchronized void esegui(Executor executor) {
 
    if (eseguita || executor == null || (evento.getDestinatario() != battello && evento.getDestinatario() != null)) { // Verifica precondizione
      return;
    }
    eseguita = true;
    
    switch (battello.getStato()) {
    
    case PRONTO:
      if (evento.getClass() == Partenza.class) {
        battello.statoCorrente = Stato.IN_NAVIGAZIONE;
        Environment.aggiungiEvento(new Percorri(battello, battello));
      }
      break;

    case IN_NAVIGAZIONE:
      if (evento.getClass() == Percorri.class) {
        if (inPorto()) {
          battello.statoCorrente = Stato.ATTRACCATO;
        }
        else if (arrivato()) {
          battello.statoCorrente = Stato.ARRIVATO;
        }
        else {
          aggiornaMtPercorsi();
          Environment.aggiungiEvento(new Percorri(battello, battello));
        }
      }
      break;
    
    case ATTRACCATO:
      if (evento.getClass() == Partenza.class) {
        battello.statoCorrente = Stato.IN_NAVIGAZIONE;
        aggiornaMtPercorsi();
        Environment.aggiungiEvento(new Percorri(battello, battello));
      }
      break;
      
    case ARRIVATO:
      break;

    default:
      throw new RuntimeException("Stato corrente non riconosciuto.");
    }
  }

  public synchronized boolean estEseguita() {
    return eseguita;
  }

  
  // Metodi ausiliari: inPorto, arrivato, aggiornaMtPercorsi
  
  private boolean inPorto() {
    // Metodo (privato) di servizio
    /*
     * Verifica se il battello e' arrivato ad un punto di attracco con bachina sufficientemente lunga
     */
    TipoLinkPercorre linkPercorre = battello.getLinkPercorre();
    double kmPercorsi = linkPercorre.getMetriPercorsi();
    Tratta tratta = linkPercorre.getTratta();

    List<TipoLinkComprende> insiemeLinkComprende = null;
    try {
      insiemeLinkComprende = tratta.getLinkComprende();
    } catch (EccezioneMoltMinMax e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      System.exit(1);
    }

    TipoLinkComprende linkComprende;
    for(int i = 0; i < insiemeLinkComprende.size(); i++) {
      linkComprende = insiemeLinkComprende.get(i);
      if (kmPercorsi == linkComprende.getDistanza() && battello.getLunghezza() <= linkComprende.getAttracco().getLunghezzaBanchina()) {
        return true;
      }
    }
    return false;
  }

  private void aggiornaMtPercorsi() {
    // Metodo (privato) di servizio
    /*
     * Rimpiazza il link esistente tra battello e tratta con uno equivalente tale
     * che kmPercorsi = nuovaDistanza
     */
    TipoLinkPercorre link = battello.getLinkPercorre();
    int nuovaDistanza = link.getMetriPercorsi() + 100;

    TipoLinkPercorre nuovoLink = null;
    try {
      Tratta tratta = link.getTratta();
      nuovoLink = new TipoLinkPercorre(battello, tratta, nuovaDistanza);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      System.exit(1);
    }
    ManagerPercorre.elimina(nuovoLink);
    ManagerPercorre.inserisci(nuovoLink);
  }

  private boolean arrivato() {
    // Metodo (privato) di servizio
    /*
     * Verifica se il battello e' arrivato
     */
    TipoLinkPercorre link = battello.getLinkPercorre();
    double kmPercorsi = link.getMetriPercorsi();
    Tratta tratta = link.getTratta();

    if (kmPercorsi >= tratta.lunghezza()) {
      return true;
    }
    return false;
  }

}
