package app.dominio;

import java.util.ArrayList;
import java.util.List;

public class Tratta {
  
  private final String nomeFiume;
  private final int minProfondita;
  private final int maxProfondita;
  
  private TipoLinkPercorre linkPercorre;
  private ArrayList<TipoLinkComprende> linkComprende;
  public final int MIN_COMPRENDE = 1;
  
  public Tratta(String nome, int minProf, int maxProf) {
    this.nomeFiume = nome;
    this.minProfondita = minProf;
    this.maxProfondita = maxProf;
    linkComprende = new ArrayList<TipoLinkComprende>();
  }

  public int lunghezza() {
    return linkComprende.get(linkComprende.size()-1).getDistanza() + 1000;
  }

  public String getNome() {
    return nomeFiume;
  }
  
  public int getMinProfondita() {
    return minProfondita;
  }
  
  public int getMaxProfondita() {
    return maxProfondita;
  }
  
  // Associazione Percorre
  public void inserisciLinkPercorre(TipoLinkPercorre l) {
    if (l != null && l.getTratta() == this) {
      ManagerPercorre.inserisci(l);
    }
  }

  public void inserisciPerManagerPercorre(ManagerPercorre m) {
    if (m != null) {
      linkPercorre = m.getLink();
    }
  }

  public void eliminaLinkPercorre(TipoLinkPercorre l) {
    if (l != null && l.getTratta() == this) {
      ManagerPercorre.elimina(l);
    }
  }

  public void eliminaPerManagerPercorre(ManagerPercorre m) {
    if (m != null) {
      linkPercorre = null;
    }
  }

  public TipoLinkPercorre getLinkPercorre() {
    return linkPercorre;
  }

  public int quantiTreni() {
    if (linkPercorre != null) {
      return 1;
    }
    return 0;
  }
  
  // Associazione Comprende
  public void inserisciLinkComprende(TipoLinkComprende l) {
    if (l != null && l.getTratta() == this) {
      ManagerComprende.inserisci(l);
    }
  }

  public void inserisciPerManagerComprende(ManagerComprende m) {
    if (m != null && !linkComprende.contains(m.getLink())) {
      linkComprende.add(m.getLink());
    }
  }

  public void eliminaLinkComprende(TipoLinkComprende l) {
    if (l != null && l.getTratta() == this) {
      ManagerComprende.elimina(l);
    }
  }

  public void eliminaPerManagerComprende(ManagerComprende m) {
    if (m != null) {
      linkComprende.remove(m.getLink());
    }
  }

  public int quantiAttracchi() {
    return linkComprende.size();
  }

  @SuppressWarnings("unchecked")
  public List<TipoLinkComprende> getLinkComprende() throws EccezioneMoltMinMax {
    if (linkComprende.size() < MIN_COMPRENDE)
      throw new EccezioneMoltMinMax("Molteplicita' minima violata");
    return (ArrayList<TipoLinkComprende>) linkComprende.clone();
  }

}
