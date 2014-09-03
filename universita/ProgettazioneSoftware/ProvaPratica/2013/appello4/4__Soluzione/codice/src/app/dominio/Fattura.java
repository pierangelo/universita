package app.dominio;

public class Fattura {
  
  private final String codice;
  
  private TipoLinkEmessaPer linkRelativaA;

  public Fattura(String codice) {
    super();
    this.codice = codice;
  }

  public String getCodice() {
    return codice;
  }
  
  public void inserisciLinkRelativaA(TipoLinkEmessaPer link) {
    if (link != null && link.getFattura() == this)
      ManagerEmessaPer.inserisci(link);
  }
  
  public void eliminaLinkRelativaA(TipoLinkEmessaPer link) {
    if (link != null && link.getFattura() == this)
      ManagerEmessaPer.elimina(link);
  }
  
  public TipoLinkEmessaPer getLinkRelativaA() throws EccezioneMoltMinMax {
    if (linkRelativaA != null)
      return linkRelativaA;
    else
      throw new EccezioneMoltMinMax("Cardinalita' minima violata");
  }
  
  public void inserisciPerManagerRelativaA(ManagerEmessaPer man) {
    if (man != null)
      linkRelativaA = man.getLink();
  }
  
  public void eliminaPerManagerRelativaA(ManagerEmessaPer man) {
    if (man != null)
      linkRelativaA = null;
  }
  
  public int quantiOrdini() {
    if (linkRelativaA == null)
      return 0;
    return 1;
  }

}
