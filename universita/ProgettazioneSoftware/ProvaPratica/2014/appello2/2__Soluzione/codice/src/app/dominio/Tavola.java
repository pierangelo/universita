package app.dominio;

public class Tavola {

	private final String marca;
	
	private TipoLinkPossiede linkPossiede;

	public Tavola(String marca) {
		this.marca = marca;
	}

	public String getMarca() {
		return marca;
	}

	// Associazione possiede
	public void inserisciLinkPossiede(TipoLinkPossiede l) {
		if (l != null && l.getTavola() == this) {
			ManagerPossiede.inserisci(l);
		}
	}

	public void inserisciPerManagerPossiede(ManagerPossiede m) {
		if (m != null) {
			linkPossiede = m.getLink();
		}
	}

	public void eliminaLinkPossiede(TipoLinkPossiede l) {
		if (l != null && l.getTavola() == this) {
			ManagerPossiede.elimina(l);
		}
	}

	public void eliminaPerManagerPossiede(ManagerPossiede m) {
		if (m != null) {
			linkPossiede = null;
		}
	}

	public TipoLinkPossiede getLinkPossiede() throws EccezioneMoltMinMax {
		if (linkPossiede == null) {
			throw new EccezioneMoltMinMax("Violazione cardinalita'!");
		}
		return linkPossiede;
	}

	public int quantiAtleti() {
		if (linkPossiede != null) {
			return 1;
		}
		return 0;
	}

}
