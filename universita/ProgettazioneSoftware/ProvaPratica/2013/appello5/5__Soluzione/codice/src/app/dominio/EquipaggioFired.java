package app.dominio;

import java.util.*;

import app._framework.*;
import app._gestioneeventi.*;
import app.dominio.Equipaggio.Stato;
import app.dominio.eventi.*;


public class EquipaggioFired implements Task {

  private boolean eseguita = false;
  private Equipaggio equipaggio;
  private Evento evento;

  public EquipaggioFired(Equipaggio equipaggio, Evento evento) {
    this.equipaggio = equipaggio;
    this.evento = evento;
  }

  public synchronized void esegui(Executor executor) {
    if (eseguita || executor == null
                 || (evento.getDestinatario() != equipaggio && evento.getDestinatario() != null)
                 || equipaggio.getLinkPartecipa() == null) { // Verifica precondizione
      return;
    }

    eseguita = true;

    switch (equipaggio.getStato()) {
    
    case PRONTO:
      
      if (evento.getClass() == Start.class) { // pronto -> inGara
        equipaggio.statoCorrente = Stato.IN_GARA;
        Environment.aggiungiEvento(new Voga(equipaggio, equipaggio));
      }
      break;

      
    case IN_GARA:
      
      if (evento.getClass() == Voga.class) {
        
        if (arrivato()) { // inGara -> finito
          // Cambia stato
          equipaggio.statoCorrente = Stato.FINITO;
        }
        else { // non e' arrivato
          if (primo()) { // inGara -> inTesta
            // Cambia stato
            equipaggio.statoCorrente = Stato.IN_TESTA;

            // Invia l'evento
            Environment.aggiungiEvento(new Voga(equipaggio, equipaggio));

            // Aggiorna i kilometri percorsi
            aggiornaKmPercorsi();
          }
          else { // non e' primo
              // Lo stato non cambia (inGara -> inGara)
            
              // Invia l'evento
              Environment.aggiungiEvento(new Voga(equipaggio, equipaggio));

              // Aggiorna i kilometri percorsi
              aggiornaKmPercorsi();
          }
        }
      }
      break;

    case IN_TESTA:
      
      if (evento.getClass() == Voga.class) {
        if (arrivato()) { // inTesta -> finito
          // Cambia stato
          equipaggio.statoCorrente = Stato.FINITO;
        } else {
          if (primo()) {
            // Non cambia stato (inTesta -> inTesta)

            // Invia l'evento
            Environment.aggiungiEvento(new Voga(equipaggio, equipaggio));

            // Aggiorna i kilometri percorsi
            aggiornaKmPercorsi();
          } else { // non e' primo
              // cambia stato (inTesta -> inGara)
              equipaggio.statoCorrente = Stato.IN_GARA;

              // Invia l'evento
              Environment.aggiungiEvento(new Voga(equipaggio, equipaggio));

              // Aggiorna i kilometri percorsi
              aggiornaKmPercorsi();
          }
        }
      }
      break;

    default:
      throw new RuntimeException("Stato corrente non riconosciuto!");
    }
  }

  private void aggiornaKmPercorsi() {
    // Metodo (privato) di servizio
    /*
     * Rimpiazza il link esistente tra regata ed equipaggio con uno equivalente tale
     * che kmPercorsi = nuovaDistanza
     */
    
    TipoLinkPartecipa link = equipaggio.getLinkPartecipa();
    
    float nuovaDistanza = 0;
    
    if (equipaggio.getStato().equals(Stato.IN_TESTA)) {
      nuovaDistanza = link.getKmPercorsi() + (link.getRegata().getDistanza() / 100) * (float) (1.1 - Math.random());
    }
    else { // inGara
      nuovaDistanza = link.getKmPercorsi() + (link.getRegata().getDistanza() / 100) * (float) (1 + Math.random());
    }
    
    Regata gara = equipaggio.getLinkPartecipa().getRegata();
    TipoLinkPartecipa nuovoLink = null;
    try {
      nuovoLink = new TipoLinkPartecipa(gara, equipaggio, nuovaDistanza);
    } catch (EccezionePrecondizioni e) {
      e.printStackTrace();
      System.exit(1);
    }
    equipaggio.eliminaLinkPartecipa(nuovoLink);
    equipaggio.inserisciLinkPartecipa(nuovoLink);
  }

  private boolean primo() {
    // Metodo (privato) di servizio
    /*
     * Calcola la condizione 'primo'
     * Restituisce true se e solo se l'equipaggio ha
     * percorso la distanza massima tra i partecipanti alla sua stessa regata
     */

    TipoLinkPartecipa link = equipaggio.getLinkPartecipa();
    float miaDistanza = link.getKmPercorsi();
    
    Set<TipoLinkPartecipa> avversari = null;
    try {
      avversari = link.getRegata().getLinkPartecipa();
    } catch (EccezioneMoltMinMax e) {
      e.printStackTrace();
      System.exit(1);
    }
    Iterator<TipoLinkPartecipa> it = avversari.iterator();
    while (it.hasNext()) {
      TipoLinkPartecipa linkCorrente = it.next();
      if (linkCorrente.getKmPercorsi() > miaDistanza) {
        // trovato un equipaggio
        // che ha percorso piu'
        // km di quello corrente
        return false;
      }
    }
    return true;
  }

  private boolean arrivato() {
    // Metodo (privato) di servizio
    /*
     * Calcola la condizione 'arrivato'
     * Restituisce true se e solo se esiste un
     * equipaggio che ha percorso tutto il tratto partenza-traguardo della regata a
     * cui partecipa l'equipaggio corrente
     */

    Set<TipoLinkPartecipa> avversari = null;
    try {
      avversari = equipaggio.getLinkPartecipa().getRegata().getLinkPartecipa();
    } catch (EccezioneMoltMinMax e) {
      e.printStackTrace();
      System.exit(1);
    }
    Iterator<TipoLinkPartecipa> it = avversari.iterator();
    while (it.hasNext()) {
      if (it.next().getKmPercorsi() >= equipaggio.getLinkPartecipa().getRegata().getDistanza()) {
        return true;
      }
    }
    return false;
  }

  public synchronized boolean estEseguita() {
    return eseguita;
  }

}
