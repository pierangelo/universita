package app.dominio;

import java.util.Observable;

import app._framework.Executor;
import app._gestioneeventi.Evento;
import app._gestioneeventi.Listener;

public class Semaforo extends Observable implements Listener {

  private final String nome;
  private int durataGiallo;
  private int durataVerde;

  private TipoLinkComprende linkComprende;

  public Semaforo(String nome, int durataGiallo, int durataVerde) {
    super();
    this.nome = nome;
    this.durataGiallo = durataGiallo;
    this.durataVerde = durataVerde;
  }

  public int getDurataGiallo() {
    return durataGiallo;
  }

  public void setDurataGiallo(int durataGiallo) {
    this.durataGiallo = durataGiallo;
  }

  public int getDurataVerde() {
    return durataVerde;
  }

  public void setDurataVerde(int durataVerde) {
    this.durataVerde = durataVerde;
  }

  public String getNome() {
    return nome;
  }

  //Associazione Comprende
  public void inserisciLinkComprende(TipoLinkComprende l) {
    if (l != null && l.getSemaforo() == this) {
      ManagerComprende.inserisci(l);
    }
  }

  public void inserisciPerManagerComprende(ManagerComprende m) {
    if (m != null) {
      linkComprende = m.getLink();
    }
  }

  public void eliminaLinkComprende(TipoLinkComprende l) {
    if (l != null && l.getSemaforo() == this) {
      ManagerComprende.elimina(l);
    }
  }

  public void eliminaPerManagerComprende(ManagerComprende m) {
    if (m != null) {
      linkComprende = null;
    }
  }

  public TipoLinkComprende getLinkComprende() throws EccezioneMoltMinMax {
    if (linkComprende != null) {
      return linkComprende;
    }
    else {
      throw new EccezioneMoltMinMax("Molteplicita' minima violata");
    }
  }

  public int quanteSimulazioni() {
    if (linkComprende == null)
      return 0;
    return 1;
  }


  //Gestione stato

  public static enum Stato {
    SPENTO, ROSSO, GIALLO, VERDE
  }

  Stato statoCorrente = Stato.SPENTO;

  public Stato getStato() {
    return statoCorrente;
  }

  public void fired(Evento e) {
    Executor.perform(new SemaforoFired(this, e));
    setChanged();
    notifyObservers();
  }

}
