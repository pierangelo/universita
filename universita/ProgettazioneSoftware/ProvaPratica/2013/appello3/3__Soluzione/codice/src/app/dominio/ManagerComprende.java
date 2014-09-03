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
    if (l != null && l.getSemaforo().quanteSimulazioni() == 0) {
      ManagerComprende m = new ManagerComprende(l);
      l.getSimulazione().inserisciPerManagerComprende(m);
      l.getSemaforo().inserisciPerManagerComprende(m);
    }
  }

  public static void elimina(TipoLinkComprende l) {
      try {
        if (l != null && l.getSemaforo().getLinkComprende().equals(l)) {
          ManagerComprende m = new ManagerComprende(l);
          l.getSimulazione().eliminaPerManagerComprende(m);
          l.getSemaforo().eliminaPerManagerComprende(m);
        }
      } catch (EccezioneMoltMinMax e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
  }

}
