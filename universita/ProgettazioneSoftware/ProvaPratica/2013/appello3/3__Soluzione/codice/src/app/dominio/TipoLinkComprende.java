package app.dominio;

public class TipoLinkComprende {
  private final Simulazione simulazione;
  private final Semaforo semaforo;

  public TipoLinkComprende(Simulazione sim, Semaforo sem) throws EccezionePrecondizioni {
    if (sim == null || sem == null)
      throw new EccezionePrecondizioni("Parametri nulli non ammessi");
    this.simulazione = sim;
    this.semaforo = sem;
  }

  public Simulazione getSimulazione() {
    return simulazione;
  }

  public Semaforo getSemaforo() {
    return semaforo;
  }

  public int hashCode() {
    return simulazione.hashCode() + semaforo.hashCode();
  }

  public boolean equals(Object o) {
    if (o == null || (this.getClass() != o.getClass()))
      return false;
    TipoLinkComprende l = (TipoLinkComprende) o;
    return (simulazione == l.getSimulazione() && semaforo == l.getSemaforo());
  }

}

