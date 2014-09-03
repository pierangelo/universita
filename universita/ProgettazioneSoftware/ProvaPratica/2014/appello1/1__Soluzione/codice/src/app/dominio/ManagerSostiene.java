package app.dominio;

public final class ManagerSostiene {
	
	private final TipoLinkSostiene link;
	
	private ManagerSostiene(TipoLinkSostiene link) {
		this.link = link;
	}

	public TipoLinkSostiene getLink() {
		return link;
	}
	
	public static void inserisci(TipoLinkSostiene l) {
		if (l != null && l.getSpesa().quantiCondomini() == 0) {
			ManagerSostiene m = new ManagerSostiene(l);
			l.getCondominio().inserisciPerManagerSostiene(m);
			l.getSpesa().inserisciPerManagerSostiene(m);
		}
	}

	public static void elimina(TipoLinkSostiene l) {
		try {
			if (l != null && l.getSpesa().getLinkSostiene().equals(l)) {
				ManagerSostiene m = new ManagerSostiene(l);
				l.getCondominio().eliminaPerManagerSostiene(m);
				l.getSpesa().eliminaPerManagerSostiene(m);
			}
		} catch (EccezioneMoltMinMax e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
