package app.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Simulazione {

  private final String codice;
  private final Date data;

  private ArrayList<TipoLinkComprende> linkComprende;
  public final int MIN_COMPRENDE = 2;

  public Simulazione(String codice, Date data) {
    super();
    this.codice = codice;
    this.data = data;
    linkComprende = new ArrayList<TipoLinkComprende>();
  }

  public String getCodice() {
    return codice;
  }

  public Date getData() {
    return data;
  }

  //Associazione Comprende
  public void inserisciLinkComprende(TipoLinkComprende l) {
    if (l != null && l.getSimulazione() == this) {
      ManagerComprende.inserisci(l);
    }
  }

  public void inserisciPerManagerComprende(ManagerComprende m) {
    if (m != null && !linkComprende.contains(m.getLink())) {
      linkComprende.add(m.getLink());
    }
  }

  public void eliminaLinkComprende(TipoLinkComprende l) {
    if (l != null && l.getSimulazione() == this) {
      ManagerComprende.elimina(l);
    }
  }

  public void eliminaPerManagerComprende(ManagerComprende m) {
    if (m != null) {
      linkComprende.remove(m.getLink());
    }
  }

  public int quantiSemafori() {
    return linkComprende.size();
  }

  @SuppressWarnings("unchecked")
  public List<TipoLinkComprende> getLinkComprende() throws EccezioneMoltMinMax {
    if (linkComprende.size() < MIN_COMPRENDE)
      throw new EccezioneMoltMinMax("Molteplicita' minima violata");
    return (ArrayList<TipoLinkComprende>) linkComprende.clone();
  }

}
