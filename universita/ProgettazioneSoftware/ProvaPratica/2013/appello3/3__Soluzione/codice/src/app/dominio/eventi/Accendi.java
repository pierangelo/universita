package app.dominio.eventi;

import app._gestioneeventi.Evento;
import app._gestioneeventi.Listener;

public class Accendi extends Evento {
  
  public Accendi(Listener m, Listener d) {
    super(m, d);
  }

  public boolean equals(Object o) {
    return super.equals(o);
  }

  public int hashCode() {
    return super.hashCode() + getClass().hashCode();
  }
}
