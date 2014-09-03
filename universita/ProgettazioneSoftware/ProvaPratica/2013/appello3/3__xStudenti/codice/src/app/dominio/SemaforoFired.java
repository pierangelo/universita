package app.dominio;

import java.util.List;

import app._framework.Executor;
import app._framework.Task;
import app._gestioneeventi.Environment;
import app._gestioneeventi.Evento;
import app.dominio.Semaforo.Stato;
import app.dominio.eventi.Accendi;
import app.dominio.eventi.Giallo;
import app.dominio.eventi.Spegni;
import app.dominio.eventi.Verde;

public class SemaforoFired implements Task {

  private boolean eseguita = false;
  private Semaforo semaforo;
  private Evento evento;

  public SemaforoFired(Semaforo semaforo, Evento evento) {
    this.semaforo = semaforo;
    this.evento = evento;
  }

  public synchronized void esegui(Executor executor) {

    if (eseguita || executor == null || (evento.getDestinatario() != semaforo && evento.getDestinatario() != null)) { // Verifica precondizione
      return;
    }
    eseguita = true;

    
    switch (semaforo.getStato()) {

    case SPENTO:
      if (evento.getClass() == Accendi.class) {
        /* DA COMPLETARE A CURA DELLO STUDENTE */
        
        
      }
      break;

    case ROSSO:
      if (evento.getClass() == Verde.class) {
        semaforo.statoCorrente = Stato.VERDE;
        Environment.aggiungiEvento(new Verde(semaforo, semaforo, semaforo.getDurataVerde()));
      }
      else if (evento.getClass() == Spegni.class) {
        semaforo.statoCorrente = Stato.SPENTO;
      }
      break;

    case VERDE:
      if (evento.getClass() == Verde.class) {
        Verde verde = (Verde)evento;
        int durata = verde.getPayload();
        if (durata > 0) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          Environment.aggiungiEvento(new Verde(semaforo, semaforo, durata-1));
        }
        else {
          semaforo.statoCorrente = Stato.GIALLO;
          Environment.aggiungiEvento(new Giallo(semaforo, semaforo, semaforo.getDurataGiallo())); 
        }
      }
      else if (evento.getClass() == Spegni.class) {
        semaforo.statoCorrente = Stato.SPENTO;
      }
      break;

    case GIALLO:
      if (evento.getClass() == Giallo.class) {
        Giallo giallo = (Giallo)evento;
        int durata = giallo.getPayload();
        if (durata > 0) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          Environment.aggiungiEvento(new Giallo(semaforo, semaforo, durata-1));
        }
        else {
          semaforo.statoCorrente = Stato.ROSSO;
          Environment.aggiungiEvento(new Verde(semaforo, prossimoSemaforo(), 0)); 
        }
      }
      else if (evento.getClass() == Spegni.class) {
        semaforo.statoCorrente = Stato.SPENTO;
      }
      break;

    default:
      throw new RuntimeException("Stato corrente non riconosciuto.");
    }
  }

  public synchronized boolean estEseguita() {
    return eseguita;
  }


  // Metodi ausiliari: inPorto, arrivato, aggiornaMtPercorsi

  private Semaforo prossimoSemaforo() {
    Semaforo prossimoSemaforo = null;
    try {
      TipoLinkComprende linkComprende = semaforo.getLinkComprende();
      Simulazione simulazione = linkComprende.getSimulazione();
      List<TipoLinkComprende> linkSemafori = simulazione.getLinkComprende();
      int indiceSemaforoCorrente = linkSemafori.indexOf(linkComprende);
      if (indiceSemaforoCorrente+1 == linkSemafori.size()) {
        prossimoSemaforo = linkSemafori.get(0).getSemaforo();
      }
      else {
        prossimoSemaforo = linkSemafori.get(indiceSemaforoCorrente+1).getSemaforo();
      }
    } catch (EccezioneMoltMinMax e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return prossimoSemaforo;
  }

}
