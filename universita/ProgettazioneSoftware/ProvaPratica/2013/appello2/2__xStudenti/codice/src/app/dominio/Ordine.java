package app.dominio;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Ordine {
  
  private final String id;
  private final Date data;
  private final boolean aDomicilio;
  
  private Cliente cliente;
  private TipoLinkRelativaA linkRelativaA;
  private HashSet<TipoLinkContiene> linkContiene;
  public static final int MIN_CONTIENE = 2; 
  
  public Ordine(String id, Date data, boolean aDomicilio) {
    super();
    this.id = id;
    this.data = data;
    this.aDomicilio = aDomicilio;
    linkContiene = new HashSet<TipoLinkContiene>();
  }

  public String getId() {
    return id;
  }

  public Date getData() {
    return data;
  }

  public boolean isaDomicilio() {
    return aDomicilio;
  }
  
  public void inserisciLinkEseguitoDa(Cliente c) {
    if (cliente == null)
      cliente = c;
  }
  
  public void eliminaLinkEseguitoDa() {
    cliente = null;
  }
  
  public Cliente getLinkEseguitoDa() throws EccezioneMoltMinMax {
    if (cliente == null)
      throw new EccezioneMoltMinMax("Molteplicita’ min/max violate");
    return cliente;
  } 
  
  public int quantiClienti() {
    if (cliente == null)
      return 0;
    return 1;
  }
  
  public void inserisciLinkRelativaA(TipoLinkRelativaA link) {
    if (link != null && link.getOrdine() == this)
      ManagerRelativaA.inserisci(link);
  }
  
  public void eliminaLinkRelativaA(TipoLinkRelativaA link) {
    if (link != null && link.getOrdine() == this)
      ManagerRelativaA.elimina(link);
  }
  
  public TipoLinkRelativaA getLinkRelativaA() throws EccezioneMoltMinMax {
    if (linkRelativaA != null)
      return linkRelativaA;
    else
      throw new EccezioneMoltMinMax("Cardinalita' minima violata");
  }
  
  public void inserisciPerManagerRelativaA(ManagerRelativaA man) {
    if (man != null)
      linkRelativaA = man.getLink();
  }
  
  public void eliminaPerManagerRelativaA(ManagerRelativaA man) {
    if (man != null)
      linkRelativaA = null;
  }
  
  public int quanteFatture() {
    if (linkRelativaA == null)
      return 0;
    return 1;
  }
  
  public void inserisciLinkContiene(TipoLinkContiene tlc) {
    if (tlc != null && tlc.getOrdine() == this) {
      linkContiene.add(tlc);
    }
  }
  
  public void eliminaLinkContiene(TipoLinkContiene tlc) {
    if (tlc != null && tlc.getOrdine() == this) {
      linkContiene.remove(tlc);
    }
  }
  
  public Set<TipoLinkContiene> getLinkContiene() throws EccezioneMoltMinMax {
    /* DA COMPLETARE A CURA DELLO STUDENTE */
    // Eliminare l'istruzione 'return null'
    
    return null;
  }
  
  public int quantiProdotti() {
    return linkContiene.size();
  }
  
  public double totale() {
    double tot = 0;
    Iterator<TipoLinkContiene> it = linkContiene.iterator();
    TipoLinkContiene link;
    while(it.hasNext()) {
      link = it.next();
      tot += link.getQuantita() * link.getProdotto().getPrezzo();
    }
    return tot;
  }

}
