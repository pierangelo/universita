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
    /* DA COMPLETARE A CURA DELLO STUDENTE */
    
    
  }

  public void inserisciPerManagerComprende(ManagerComprende m) {
    /* DA COMPLETARE A CURA DELLO STUDENTE */
    
    
  }

  public void eliminaLinkComprende(TipoLinkComprende l) {
    /* DA COMPLETARE A CURA DELLO STUDENTE */
    
    
  }

  public void eliminaPerManagerComprende(ManagerComprende m) {
    /* DA COMPLETARE A CURA DELLO STUDENTE */
    
    
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
