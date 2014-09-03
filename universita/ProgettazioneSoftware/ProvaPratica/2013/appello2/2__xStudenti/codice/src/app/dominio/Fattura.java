package app.dominio;

public class Fattura {
  
  private final String codice;
  
  private TipoLinkRelativaA linkRelativaA;

  public Fattura(String codice) {
    super();
    this.codice = codice;
  }

  public String getCodice() {
    return codice;
  }
  
  public void inserisciLinkRelativaA(TipoLinkRelativaA link) {
    if (link != null && link.getFattura() == this)
      ManagerRelativaA.inserisci(link);
  }
  
  public void eliminaLinkRelativaA(TipoLinkRelativaA link) {
    if (link != null && link.getFattura() == this)
      ManagerRelativaA.elimina(link);
  }
  
  public TipoLinkRelativaA getLinkRelativaA() throws EccezioneMoltMinMax {
    if (linkRelativaA != null)
      return linkRelativaA;
    else
      throw new EccezioneMoltMinMax("Cardinalita' minima violata");
  }
  
  public void inserisciPerManagerRelativaA(ManagerRelativaA man) {
    /* DA COMPLETARE A CURA DELLO STUDENTE */
    
    
  }
  
  public void eliminaPerManagerRelativaA(ManagerRelativaA man) {
    /* DA COMPLETARE A CURA DELLO STUDENTE */
    
    
  }
  
  public int quantiOrdini() {
    if (linkRelativaA == null)
      return 0;
    return 1;
  }

}
