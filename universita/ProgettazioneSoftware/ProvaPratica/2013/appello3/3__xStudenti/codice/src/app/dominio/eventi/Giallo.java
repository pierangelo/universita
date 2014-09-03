package app.dominio.eventi;

import app._gestioneeventi.Evento;
import app._gestioneeventi.Listener;

public class Giallo extends Evento {

  private final int durata;

  public Giallo(Listener m, Listener d, int payload) {
    super(m, d);
    this.durata = payload;
  }

  public boolean equals(Object o) {
    if (super.equals(o)) {
      Giallo e = (Giallo) o;
      return durata == e.durata;
    }
    else return false;
  }

  public int getPayload() {
    return durata;
  }

  public int hashCode() {
    return super.hashCode() + getClass().hashCode();
  }
}
