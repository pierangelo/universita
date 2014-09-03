package app.dominio;

public final class ManagerInclude {

	private final TipoLinkInclude link;

	private ManagerInclude(TipoLinkInclude link) {
		this.link = link;
	}

	public TipoLinkInclude getLink() {
		return link;
	}

	public static void inserisci(TipoLinkInclude l) {
		if (l != null && l.getImmobile().quantiCondomini() == 0) {
			ManagerInclude m = new ManagerInclude(l);
			l.getCondominio().inserisciPerManagerInclude(m);
			l.getImmobile().inserisciPerManagerInclude(m);
		}
	}

	public static void elimina(TipoLinkInclude l) {
		try {
			if (l != null && l.getImmobile().getLinkInclude().equals(l)) {
				ManagerInclude m = new ManagerInclude(l);
				l.getCondominio().eliminaPerManagerInclude(m);
				l.getImmobile().eliminaPerManagerInclude(m);
			}
		} catch (EccezioneMoltMinMax e) {
			e.printStackTrace();
		}
	}


}
