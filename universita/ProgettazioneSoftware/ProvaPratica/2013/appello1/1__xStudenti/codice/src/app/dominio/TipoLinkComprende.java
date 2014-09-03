package app.dominio;

public class TipoLinkComprende {
  private final Attracco attracco;
  private final Tratta tratta;
  private final int distanza;

  public TipoLinkComprende(Tratta l, Attracco f, int d) throws EccezionePrecondizioni {
    if (f == null || l == null)
      throw new EccezionePrecondizioni("Parametri nulli non ammessi");
    this.attracco = f;
    this.tratta = l;
    this.distanza = d;
  }

  public Attracco getAttracco() {
    return attracco;
  }

  public Tratta getTratta() {
    return tratta;
  }
  
  public int getDistanza() {
    return distanza;
  }

  public int hashCode() {
    return attracco.hashCode() + tratta.hashCode();
  }

  public boolean equals(Object o) {
    if (o == null || (this.getClass() != o.getClass()))
      return false;
    TipoLinkComprende l = (TipoLinkComprende) o;
    return (attracco == l.getAttracco() && tratta == l.getTratta());
  }

}

