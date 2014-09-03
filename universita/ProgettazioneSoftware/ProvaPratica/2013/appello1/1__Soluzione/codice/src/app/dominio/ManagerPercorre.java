package app.dominio;

public class ManagerPercorre {
  
  private TipoLinkPercorre link;

  private ManagerPercorre(TipoLinkPercorre link) {
    this.link = link;
  }

  public TipoLinkPercorre getLink() {
    return link;
  }

  public static void inserisci(TipoLinkPercorre l) {
    if (l != null && l.getBattello().getLinkPercorre() == null && l.getTratta().getLinkPercorre() == null) {
      ManagerPercorre m = new ManagerPercorre(l);
      l.getBattello().inserisciPerManagerPercorre(m);
      l.getTratta().inserisciPerManagerPercorre(m);
    }
  }

  public static void elimina(TipoLinkPercorre l) {
    if (l != null && l.getBattello().getLinkPercorre().equals(l)) {
      ManagerPercorre m = new ManagerPercorre(l);
      l.getBattello().eliminaPerManagerPercorre(m);
      l.getTratta().eliminaPerManagerPercorre(m);
    }
  }

}
