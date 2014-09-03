package app.dominio;

public class TipoLinkPercorre {

  private final Battello battello;
  private final Tratta tratta;
  private final int mtPercorsi;

  public TipoLinkPercorre(Battello t, Tratta l, int mt) throws EccezionePrecondizioni {
    if (t == null || l == null)
      throw new EccezionePrecondizioni("Parametri nulli non ammessi");
    this.battello = t;
    this.tratta = l;
    this.mtPercorsi = mt;
  }

  public Battello getBattello() {
    return battello;
  }

  public Tratta getTratta() {
    return tratta;
  }

  public int getMetriPercorsi() {
    return mtPercorsi;
  }

  public int hashCode() {
    return battello.hashCode() + tratta.hashCode();
  }

  public boolean equals(Object o) {
    if (o == null || (this.getClass() != o.getClass()))
      return false;
    TipoLinkPercorre l = (TipoLinkPercorre) o;
    return (battello == l.getBattello() && tratta == l.getTratta());
  }

}
