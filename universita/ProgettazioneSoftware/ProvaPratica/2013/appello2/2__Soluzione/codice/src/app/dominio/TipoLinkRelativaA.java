package app.dominio;

public class TipoLinkRelativaA {
  
  private final Fattura laFattura;
  private final Ordine loOrdine;

  public TipoLinkRelativaA(Fattura f, Ordine o) throws EccezionePrecondizioni {
    if (f == null || o == null) // CONTROLLO PRECONDIZIONI
      throw new EccezionePrecondizioni("Gli oggetti devono essere inizializzati");
    laFattura = f;
    loOrdine = o;
  }

  public boolean equals(Object o) {
    if (o != null && getClass().equals(o.getClass())) {
      TipoLinkRelativaA b = (TipoLinkRelativaA) o;
      return b.laFattura == laFattura
          && b.loOrdine == loOrdine;
    }
    else
      return false;
  }

  public int hashCode() {
    return laFattura.hashCode() + loOrdine.hashCode();
  }

  public Fattura getFattura() {
    return laFattura;
  }

  public Ordine getOrdine() {
    return loOrdine;
  }
 
}
