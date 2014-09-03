package app.dominio;

public class TipoLinkVince {

	private final Atleta atleta;
	private final Gara gara;

	public TipoLinkVince(Gara gara, Atleta atleta) throws EccezionePrecondizioni {
		if (gara == null || atleta == null) {
			throw new EccezionePrecondizioni("Parametri nulli non ammessi");
		}
		this.atleta = atleta;
		this.gara = gara;
	}

	public Gara getGara() {
		return gara;
	}

	public Atleta getAtleta() {
		return atleta;
	}

	public boolean equals(Object o) {
		if (o == null || (this.getClass() != o.getClass()))
			return false;
		TipoLinkVince l = (TipoLinkVince) o;
		return (gara == l.getGara() && atleta == l.getAtleta());
	}

	public int hashCode() {
		return gara.hashCode() + atleta.hashCode();
	}

}
