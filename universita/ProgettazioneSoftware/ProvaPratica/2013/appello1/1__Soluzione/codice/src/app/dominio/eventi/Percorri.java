package app.dominio.eventi;

import app._gestioneeventi.Evento;
import app._gestioneeventi.Listener;

public class Percorri extends Evento {
  
  public Percorri(Listener m, Listener d) {
    super(m, d);
  }

  public boolean equals(Object o) {
    return super.equals(o);
  }

  public int hashCode() {
    return super.hashCode() + getClass().hashCode();
  }
}
