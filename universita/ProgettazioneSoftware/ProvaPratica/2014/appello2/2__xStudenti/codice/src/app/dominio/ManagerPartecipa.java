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
		if (l != null && l.getAtleta().quanteGare() == 0) {
			ManagerPartecipa m = new ManagerPartecipa(l);
			l.getGara().inserisciPerManagerPartecipa(m);
			l.getAtleta().inserisciPerManagerPartecipa(m);
		}
	}

	public static void elimina(TipoLinkPartecipa l) {
		if (l != null && l.getAtleta().getLinkPartecipa().equals(l)) {
			ManagerPartecipa m = new ManagerPartecipa(l);
			l.getGara().eliminaPerManagerPartecipa(m);
			l.getAtleta().eliminaPerManagerPartecipa(m);
		}
	}

}
