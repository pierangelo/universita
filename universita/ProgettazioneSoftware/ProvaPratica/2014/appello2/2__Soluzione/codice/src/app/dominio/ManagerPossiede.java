package app.dominio;

public class ManagerPossiede {

	private TipoLinkPossiede link;

	private ManagerPossiede(TipoLinkPossiede link) {
		this.link = link;
	}

	public TipoLinkPossiede getLink() {
		return link;
	}

	public static void inserisci(TipoLinkPossiede l) {
		if (l != null && l.getAtleta().quanteTavole() == 0 && l.getTavola().quantiAtleti() == 0) {
			ManagerPossiede m = new ManagerPossiede(l);
			l.getAtleta().inserisciPerManagerPossiede(m);
			l.getTavola().inserisciPerManagerPossiede(m);
		}
	}

	public static void elimina(TipoLinkPossiede l) {
		try {
			if (l != null && l.getAtleta().getLinkPossiede().equals(l)) {
				ManagerPossiede m = new ManagerPossiede(l);
				l.getAtleta().eliminaPerManagerPossiede(m);
				l.getTavola().eliminaPerManagerPossiede(m);
			}
		} catch (EccezioneMoltMinMax e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
