package app.dominio;

public class TipoLinkPossiede {

	private final Atleta atleta;
	private final Tavola tavola;

	public TipoLinkPossiede(Atleta atleta, Tavola tavola) throws EccezionePrecondizioni {
		if (atleta == null || tavola == null) {
			throw new EccezionePrecondizioni("Parametri nulli non ammessi");
		}
		this.atleta = atleta;
		this.tavola = tavola;
	}

	public Tavola getTavola() {
		return tavola;
	}

	public Atleta getAtleta() {
		return atleta;
	}

	public boolean equals(Object o) {
		if (o == null || (this.getClass() != o.getClass()))
			return false;
		TipoLinkPossiede l = (TipoLinkPossiede) o;
		return (tavola == l.getTavola() && atleta == l.getAtleta());
	}

	public int hashCode() {
		return tavola.hashCode() + atleta.hashCode();
	}
	
}
