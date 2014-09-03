package app.dominio;

public class ManagerVince {

	private TipoLinkVince link;

	private ManagerVince(TipoLinkVince link) {
		this.link = link;
	}

	public TipoLinkVince getLink() {
		return link;
	}

	public static void inserisci(TipoLinkVince l) {
		if (l != null && l.getAtleta().quanteGareVinte() == 0) {
			ManagerVince m = new ManagerVince(l);
			l.getGara().inserisciPerManagerVince(m);
			l.getAtleta().inserisciPerManagerVince(m);
		}
	}

	public static void elimina(TipoLinkVince l) {
		try {
			if (l != null && l.getAtleta().getLinkVince().equals(l)) {
				ManagerVince m = new ManagerVince(l);
				l.getGara().eliminaPerManagerVince(m);
				l.getAtleta().eliminaPerManagerVince(m);
			}
		} catch (EccezioneSubset e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
