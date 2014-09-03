package app.dominio;

public final class ManagerPossiede {

	private final TipoLinkPossiede link;

	private ManagerPossiede(TipoLinkPossiede link) {
		this.link = link;
	}

	public TipoLinkPossiede getLink() {
		return link;
	}

	public static void inserisci(TipoLinkPossiede l) {
		if (l != null) {
			ManagerPossiede m = new ManagerPossiede(l);
			l.getProprietario().inserisciPerManagerPossiede(m);
			l.getImmobile().inserisciPerManagerPossiede(m);
		}
	}

	public static void elimina(TipoLinkPossiede l) {

		if (l != null) {
			ManagerPossiede m = new ManagerPossiede(l);
			l.getProprietario().eliminaPerManagerPossiede(m);
			l.getImmobile().eliminaPerManagerPossiede(m);
		}

	}



}
