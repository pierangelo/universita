package app.dominio;

public class ManagerComprende {
  
  private TipoLinkComprende link;

  private ManagerComprende(TipoLinkComprende link) {
    this.link = link;
  }

  public TipoLinkComprende getLink() {
    return link;
  }

  public static void inserisci(TipoLinkComprende l) {
    if (l != null && l.getAttracco().quanteTratte() == 0) {
      ManagerComprende m = new ManagerComprende(l);
      l.getAttracco().inserisciPerManagerComprende(m);
      l.getTratta().inserisciPerManagerComprende(m);
    }
  }

  public static void elimina(TipoLinkComprende l) {
      try {
        if (l != null && l.getAttracco().getLinkComprende().equals(l)) {
          ManagerComprende m = new ManagerComprende(l);
          l.getAttracco().eliminaPerManagerComprende(m);
          l.getTratta().eliminaPerManagerComprende(m);
        }
      } catch (EccezioneMoltMinMax e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
  }

}
