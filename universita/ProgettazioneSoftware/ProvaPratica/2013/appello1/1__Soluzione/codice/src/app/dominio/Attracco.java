package app.dominio;

public class Attracco {

  private final String nome;
  private int lunghezzaBanchina;

  private TipoLinkComprende linkComprende;

  public Attracco(String nome, int banchina) {
    this.nome = nome;
    this.lunghezzaBanchina = banchina;
  }

  public String getNome() {
    return nome;
  }

  public int getLunghezzaBanchina() {
    return lunghezzaBanchina;
  }
  
  public void setLunghezzaBanchina(int l) {
    lunghezzaBanchina = l;
  }

  // Associazione Comprende
  public void inserisciLinkComprende(TipoLinkComprende l) {
    if (l != null && l.getAttracco() == this) {
      ManagerComprende.inserisci(l);
    }
  }

  public void inserisciPerManagerComprende(ManagerComprende m) {
    if (m != null) {
      linkComprende = m.getLink();
    }
  }

  public void eliminaLinkComprende(TipoLinkComprende l) {
    if (l != null && l.getAttracco() == this) {
      ManagerComprende.elimina(l);
    }
  }

  public void eliminaPerManagerComprende(ManagerComprende m) {
    if (m != null) {
      linkComprende = null;
    }
  }

  public TipoLinkComprende getLinkComprende() throws EccezioneMoltMinMax {
    if (linkComprende != null) {
      return linkComprende;
    }
    else {
      throw new EccezioneMoltMinMax("Molteplicita' minima violata");
    }
  }

  public int quanteTratte() {
    if (linkComprende == null)
      return 0;
    return 1;
  }

}
