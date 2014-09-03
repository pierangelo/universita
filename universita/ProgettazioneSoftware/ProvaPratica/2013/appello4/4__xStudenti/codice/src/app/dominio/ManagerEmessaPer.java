package app.dominio;

public class ManagerEmessaPer {

  private TipoLinkEmessaPer link;

  private ManagerEmessaPer(TipoLinkEmessaPer x) {
    link = x;
  }

  public TipoLinkEmessaPer getLink() {
    return link;
  }

  public static void inserisci(TipoLinkEmessaPer y) {
    if (y != null && y.getFattura().quantiOrdini()==0 && y.getOrdine().quanteFatture()==0) {
      ManagerEmessaPer k = new ManagerEmessaPer(y);
      y.getFattura().inserisciPerManagerRelativaA(k);
      y.getOrdine().inserisciPerManagerEmessaPer(k);
    }
  }

  public static void elimina(TipoLinkEmessaPer y) {
    try {
      if(y!=null && y.getFattura().getLinkRelativaA().equals(y) && y.getOrdine().getLinkEmessaPer().equals(y)) {
        ManagerEmessaPer man = new ManagerEmessaPer(y);
        y.getFattura().eliminaPerManagerRelativaA(man);
        y.getOrdine().eliminaPerManagerEmessaPer(man);
      }
    } catch (EccezioneMoltMinMax e) {
      e.printStackTrace();
    }
  }
}