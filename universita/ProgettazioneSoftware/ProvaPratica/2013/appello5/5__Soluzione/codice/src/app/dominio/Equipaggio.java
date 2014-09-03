package app.dominio;

import java.util.Observable;

import app._framework.*;
import app._gestioneeventi.*;

public class Equipaggio extends Observable implements Listener {
  
  private final String nome;
  private TipoLinkPartecipa partecipa;

  public Equipaggio(String nome) {
    this.nome = nome;
    partecipa = null;
  }

  public String getNome() {
    return nome;
  }

  public void inserisciLinkPartecipa(TipoLinkPartecipa l) {
    if (l != null && l.getEquipaggio() == this) {
      ManagerPartecipa.inserisci(l);
    }
  }

  public void inserisciPerManagerPartecipa(ManagerPartecipa m) {
    if (m != null) {
      partecipa = m.getLink();
      setChanged();
      notifyObservers(m.getLink().getKmPercorsi());
    }
  }

  public void eliminaLinkPartecipa(TipoLinkPartecipa l) {
    if (l != null && l.getEquipaggio() == this) {
      ManagerPartecipa.elimina(l);
    }
  }

  public void eliminaPerManagerPartecipa(ManagerPartecipa m) {
    if (m != null) {
      partecipa = null;
    }
  }

  public TipoLinkPartecipa getLinkPartecipa() {
    return partecipa;
  }

  public int quanteGare() {
    if (partecipa != null) {
      return 1;
    }
    return 0;
  }

  
  // gestione stato

  public static enum Stato {
    PRONTO, IN_GARA, IN_TESTA, FINITO
  }

  Stato statoCorrente = Stato.PRONTO;

  public Stato getStato() {
    return statoCorrente;
  }

  public void fired(Evento e) {
    Executor.perform(new EquipaggioFired(this, e));
    setChanged();
    notifyObservers();
  }
}
