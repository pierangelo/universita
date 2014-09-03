package app.dominio;

public class TipoLinkSostiene {
	
	private final Condominio condominio;
	private final Spesa spesa;
	
	public TipoLinkSostiene(Condominio condominio, Spesa spesa) throws EccezionePrecondizioni {
		
		if (condominio == null || spesa == null) {
			throw new EccezionePrecondizioni("Parametri nulli non ammessi!");
		}
		
		this.condominio = condominio;
		this.spesa = spesa;
	}

	public Condominio getCondominio() {
		return condominio;
	}

	public Spesa getSpesa() {
		return spesa;
	}
	
	public int hashCode() {
		return condominio.hashCode() + spesa.hashCode();
	}

	public boolean equals(Object o) {
		if (o == null || (this.getClass() != o.getClass()))
			return false;
		
		TipoLinkSostiene l = (TipoLinkSostiene) o;
		return (condominio == l.condominio && spesa == l.spesa);
	}
	

}
