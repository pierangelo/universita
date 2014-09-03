package app.dominio;

public class TipoLinkContiene {
  
  private final Ordine loOrdine;
  private final Prodotto ilProdotto;
  private int quantita;

  public TipoLinkContiene(Ordine o, Prodotto p, int q) throws EccezionePrecondizioni {
    if (o == null || p == null) // CONTROLLO PRECONDIZIONI
      throw new EccezionePrecondizioni("Gli oggetti devono essere inizializzati");
    loOrdine = o;
    ilProdotto = p;
    quantita = q;
  }

  public boolean equals(Object o) {
    if (o != null && getClass().equals(o.getClass())) {
      TipoLinkContiene b = (TipoLinkContiene) o;
      return b.loOrdine == loOrdine
          && b.ilProdotto == ilProdotto;
    }
    else
      return false;
  }

  public int hashCode() {
    return loOrdine.hashCode() + ilProdotto.hashCode();
  }

  public Ordine getOrdine() {
    return loOrdine;
  }

  public Prodotto getProdotto() {
    return ilProdotto;
  }

  public int getQuantita() {
    return quantita;
  }
  
}
