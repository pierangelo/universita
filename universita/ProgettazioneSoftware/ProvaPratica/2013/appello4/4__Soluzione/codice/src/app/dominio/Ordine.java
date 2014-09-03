package app.dominio;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Ordine {
  
  private final String id;
  private final Date dataEora;
  
  private Tavolo tavolo;
  private TipoLinkEmessaPer linkEmessaPer;
  private HashSet<TipoLinkContiene> linkContiene;
  public static final int MIN_CONTIENE = 1; 
  
  public Ordine(String id, Date data) {
    super();
    this.id = id;
    this.dataEora = data;
    linkContiene = new HashSet<TipoLinkContiene>();
  }

  public String getId() {
    return id;
  }

  public Date getData() {
    return dataEora;
  }

  
  public void inserisciLinkRelativoA(Tavolo c) {
    if (tavolo == null)
      tavolo = c;
  }
  
  public void eliminaLinkRelativoA() {
    tavolo = null;
  }
  
  public Tavolo getLinkRelativoA() throws EccezioneMoltMinMax {
    if (tavolo == null)
      throw new EccezioneMoltMinMax("Molteplicita’ min/max violate");
    return tavolo;
  } 
  
  public int quantiTavoli() {
    if (tavolo == null)
      return 0;
    return 1;
  }
  
  
  public void inserisciLinkEmessaPer(TipoLinkEmessaPer link) {
    if (link != null && link.getOrdine() == this)
      ManagerEmessaPer.inserisci(link);
  }
  
  public void eliminaLinkEmessaPer(TipoLinkEmessaPer link) {
    if (link != null && link.getOrdine() == this)
      ManagerEmessaPer.elimina(link);
  }
  
  public TipoLinkEmessaPer getLinkEmessaPer() throws EccezioneMoltMinMax {
    if (linkEmessaPer != null)
      return linkEmessaPer;
    else
      throw new EccezioneMoltMinMax("Cardinalita' minima violata");
  }
  
  public void inserisciPerManagerEmessaPer(ManagerEmessaPer man) {
    if (man != null)
      linkEmessaPer = man.getLink();
  }
  
  public void eliminaPerManagerEmessaPer(ManagerEmessaPer man) {
    if (man != null)
      linkEmessaPer = null;
  }
  
  public int quanteFatture() {
    if (linkEmessaPer == null)
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
    if (linkContiene.size() < MIN_CONTIENE)
      throw new EccezioneMoltMinMax("Molteplicita' minima violata");
    return (HashSet<TipoLinkContiene>) linkContiene.clone();
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
