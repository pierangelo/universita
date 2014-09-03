package app.dominio.eventi;

import app._gestioneeventi.Evento;
import app._gestioneeventi.Listener;

public class Verde extends Evento {
  
  private final int durata;
  
  public Verde(Listener m, Listener d, int payload) {
    super(m, d);
    this.durata = payload;
  }

  public boolean equals(Object o) {
    if (super.equals(o)) {
      Verde e = (Verde) o;
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
