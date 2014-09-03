package app.dominio;

import java.util.Observable;

import app._framework.Executor;
import app._gestioneeventi.Evento;
import app._gestioneeventi.Listener;

public class Battello extends Observable implements Listener {
  
  private final String nome;
  private final int numeroPosti;
  private final int lunghezza;
  private final int profondita;
  private TipoLinkPercorre linkPercorre;
  
  public Battello(String nome, int numeroPosti, int lunghezza, int profondita) {
    this.nome = nome;
    this.numeroPosti = numeroPosti;
    this.lunghezza = lunghezza;
    this.profondita = profondita;
  }
  
  public String getNome() {
    return nome;
  }

  public int getNumeroPosti() {
    return numeroPosti;
  }

  public int getLunghezza() {
    return lunghezza;
  }

  public int getProfondita() {
    return profondita;
  }
  
  //Associazione Percorre
  public void inserisciLinkPercorre(TipoLinkPercorre l) {
    if (l != null && l.getBattello() == this) {
      ManagerPercorre.inserisci(l);
    }
  }

  public void inserisciPerManagerPercorre(ManagerPercorre m) {
    if (m != null) {
      linkPercorre = m.getLink();
      setChanged();
      notifyObservers(m.getLink().getMetriPercorsi());
    }
  }

  public void eliminaLinkPercorre(TipoLinkPercorre l) {
    if (l != null && l.getBattello() == this) {
      ManagerPercorre.elimina(l);
    }
  }

  public void eliminaPerManagerPercorre(ManagerPercorre m) {
    if (m != null) {
      linkPercorre = null;
    }
  }

  public TipoLinkPercorre getLinkPercorre() {
    return linkPercorre;
  }

  public int quanteTratte() {
    if (linkPercorre != null) {
      return 1;
    }
    return 0;
  }
  
  
  // Gestione stato

  public static enum Stato {
    PRONTO, IN_NAVIGAZIONE, ATTRACCATO, ARRIVATO
  }

  Stato statoCorrente = Stato.PRONTO;

  public Stato getStato() {
    return statoCorrente;
  }

  public void fired(Evento e) {
    Executor.perform(new BattelloFired(this, e));
    setChanged();
    notifyObservers();
  }
  

}
