package app.dominio;

import java.util.HashSet;
import java.util.Set;

public abstract class Immobile {

	protected final int metriQuadri;
	protected final int interno;
	protected final int piano;
	protected double millesimi;

	protected TipoLinkInclude condominio;
	
	protected HashSet<TipoLinkPossiede> insiemeLinkPossiede;
	
	public static final int MIN_POSSIEDE = 1;

	public Immobile(int metriQuadri, int interno, int piano) {
		this.metriQuadri = metriQuadri;
		this.interno = interno;
		this.piano = piano;
		insiemeLinkPossiede = new HashSet<TipoLinkPossiede>();
	}

	public double getMillesimi() {
		return millesimi;
	}

	public void setMillesimi(double millesimi) {
		this.millesimi = millesimi;
	}

	public int getMetriQuadri() {
		return metriQuadri;
	}

	public int getInterno() {
		return interno;
	}

	public int getPiano() {
		return piano;
	}

	// Associazione Include
	public void inserisciLinkInclude(TipoLinkInclude l) {
		if (l != null && l.getImmobile() == this) {
			ManagerInclude.inserisci(l);
		}
	}

	public void inserisciPerManagerInclude(ManagerInclude m) {
		if (m != null) {
			condominio = m.getLink();
		}
	}

	public void eliminaLinkInclude(TipoLinkInclude l) {
		if (l != null && l.getImmobile() == this) {
			ManagerInclude.elimina(l);
		}
	}

	public void eliminaPerManagerInclude(ManagerInclude m) {
		if (m != null) {
			condominio = null;
		}
	}

	public TipoLinkInclude getLinkInclude() throws EccezioneMoltMinMax {
		if (condominio == null)
			throw new EccezioneMoltMinMax("Cardinalita' min/max violata");
		return condominio;
	}

	public int quantiCondomini() {
		if (condominio == null)
			return 0;
		return 1;
	}
	
	// Associazione Possiede
	public void inserisciLinkPossiede(TipoLinkPossiede l) {
		if (l != null && l.getImmobile() == this) {
			ManagerPossiede.inserisci(l);
		}
	}

	public void inserisciPerManagerPossiede(ManagerPossiede m) {
		if (m != null) {
			insiemeLinkPossiede.add(m.getLink());
		}
	}

	public void eliminaLinkPossiede(TipoLinkPossiede l) {
		if (l != null && l.getImmobile() == this) {
			ManagerPossiede.elimina(l);
		}
	}

	public void eliminaPerManagerPossiede(ManagerPossiede m) {
		if (m != null) {
			insiemeLinkPossiede.remove(m.getLink());
		}
	}

	public Set<TipoLinkPossiede> getLinkPossiede() throws EccezioneMoltMinMax {
		if (quantiProprietari() < MIN_POSSIEDE)
			throw new EccezioneMoltMinMax("Cardinalita' minima violata");
		return (HashSet<TipoLinkPossiede>) insiemeLinkPossiede.clone();
	}

	public int quantiProprietari() {
		return insiemeLinkPossiede.size();
	}

}
