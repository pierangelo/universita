package app.dominio;

public class ManagerPartecipa {
  private TipoLinkPartecipa link;

  private ManagerPartecipa(TipoLinkPartecipa link) {
    this.link = link;
  }

  public TipoLinkPartecipa getLink() {
    return link;
  }

  public static void inserisci(TipoLinkPartecipa l) {
    if (l != null && l.getEquipaggio().quanteGare() == 0) {
      ManagerPartecipa m = new ManagerPartecipa(l);
      l.getRegata().inserisciPerManagerPartecipa(m);
      l.getEquipaggio().inserisciPerManagerPartecipa(m);
    }
  }

  public static void elimina(TipoLinkPartecipa l) {
    if (l != null && l.getEquipaggio().getLinkPartecipa().equals(l)) {
      ManagerPartecipa m = new ManagerPartecipa(l);
      l.getRegata().eliminaPerManagerPartecipa(m);
      l.getEquipaggio().eliminaPerManagerPartecipa(m);
    }
  }

}
