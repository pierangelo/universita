package app.dominio;

public class TipoLinkInclude {

	private final Condominio condominio;
	private final Immobile immobile;

	public TipoLinkInclude(Condominio condominio, Immobile immobile) throws EccezionePrecondizioni {

		if (condominio == null || immobile == null) {
			throw new EccezionePrecondizioni("Parametri nulli non ammessi!");
		}

		this.condominio = condominio;
		this.immobile = immobile;
	}

	public Condominio getCondominio() {
		return condominio;
	}

	public Immobile getImmobile() {
		return immobile;
	}

	public int hashCode() {
		return condominio.hashCode() + immobile.hashCode();
	}

	public boolean equals(Object o) {
		if (o == null || (this.getClass() != o.getClass()))
			return false;
		
		TipoLinkInclude l = (TipoLinkInclude) o;
		return (condominio == l.condominio && immobile == l.immobile);
	}

}
