package app.dominio.eventi;

import app._gestioneeventi.*;

public class Start extends Evento {
  public Start(Listener m, Listener d) {
    super(m, d);
  }

  public boolean equals(Object o) {
    return super.equals(o);
  }

  public int hashCode() {
    return super.hashCode() + getClass().hashCode();
  }
}
