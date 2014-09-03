package app.dominio;

public class TipoLinkPartecipa {

	private final Gara gara;
	private final Atleta atleta;
	private final double mtPercorsi;

	public TipoLinkPartecipa(Gara gara, Atleta atleta, double mt) throws EccezionePrecondizioni {
		if (gara == null || atleta == null) {
			throw new EccezionePrecondizioni("Parametri nulli non ammessi");
		}
		this.atleta = atleta;
		this.gara = gara;
		this.mtPercorsi = mt;
	}

	public Gara getGara() {
		return gara;
	}

	public Atleta getAtleta() {
		return atleta;
	}

	public double getMtPercorsi() {
		return mtPercorsi;
	}

	public boolean equals(Object o) {
		if (o == null || (this.getClass() != o.getClass()))
			return false;
		TipoLinkPartecipa l = (TipoLinkPartecipa) o;
		return (gara == l.getGara() && atleta == l.getAtleta());
	}

	public int hashCode() {
		return gara.hashCode() + atleta.hashCode();
	}

}
