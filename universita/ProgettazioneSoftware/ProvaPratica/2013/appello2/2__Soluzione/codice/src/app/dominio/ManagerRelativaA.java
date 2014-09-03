package app.dominio;

public class ManagerRelativaA {

  private TipoLinkRelativaA link;

  private ManagerRelativaA(TipoLinkRelativaA x) {
    link = x;
  }

  public TipoLinkRelativaA getLink() {
    return link;
  }

  public static void inserisci(TipoLinkRelativaA y) {
    if (y != null && y.getFattura().quantiOrdini()==0 && y.getOrdine().quanteFatture()==0) {
      ManagerRelativaA k = new ManagerRelativaA(y);
      y.getFattura().inserisciPerManagerRelativaA(k);
      y.getOrdine().inserisciPerManagerRelativaA(k);
    }
  }

  public static void elimina(TipoLinkRelativaA y) {
    try {
      if(y!=null && y.getFattura().getLinkRelativaA().equals(y) && y.getOrdine().getLinkRelativaA().equals(y)) {
        ManagerRelativaA man = new ManagerRelativaA(y);
        y.getFattura().eliminaPerManagerRelativaA(man);
        y.getOrdine().eliminaPerManagerRelativaA(man);
      }
    } catch (EccezioneMoltMinMax e) {
      e.printStackTrace();
    }
  }
}