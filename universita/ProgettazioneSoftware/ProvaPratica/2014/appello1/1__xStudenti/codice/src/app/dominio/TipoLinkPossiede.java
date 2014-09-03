package app.dominio;

public class TipoLinkPossiede {
	
	private final Proprietario proprietario;
	private final Immobile immobile;
	private final int quota;
	
	public TipoLinkPossiede(Proprietario proprietario, Immobile immobile, int quota) throws EccezionePrecondizioni {
		
		if (proprietario == null || immobile == null) {
			throw new EccezionePrecondizioni("Parametri nulli non ammessi!");
		}
		
		this.proprietario = proprietario;
		this.immobile = immobile;
		this.quota = quota;
	}

	public int getQuota() {
		return quota;
	}

	public Proprietario getProprietario() {
		return proprietario;
	}

	public Immobile getImmobile() {
		return immobile;
	}
	
	public int hashCode() {
		return immobile.hashCode() + proprietario.hashCode();
	}

	public boolean equals(Object o) {
		if (o == null || (this.getClass() != o.getClass()))
			return false;
		
		TipoLinkPossiede l = (TipoLinkPossiede) o;
		return (immobile == l.immobile && proprietario == l.proprietario);
	}
	
	

}
