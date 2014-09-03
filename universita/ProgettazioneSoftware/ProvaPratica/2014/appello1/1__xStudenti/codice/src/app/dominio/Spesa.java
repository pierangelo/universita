package app.dominio;

public abstract class Spesa {

	protected final String codice;
	protected final double importo;
	protected final int anno;
	protected final String descrizione;

	protected TipoLinkSostiene condominio;

	public Spesa(String codice, double importo, int anno, String descrizione) {
		this.codice = codice;
		this.importo = importo;
		this.anno = anno;
		this.descrizione = descrizione;
	}

	public String getCodice() {
		return codice;
	}

	public double getImporto() {
		return importo;
	}

	public int getAnno() {
		return anno;
	}

	public String getDescrizione() {
		return descrizione;
	}

	// Associazione Sostiene
	public void inserisciLinkSostiene(TipoLinkSostiene l) {
		if (l != null && l.getSpesa() == this) {
			ManagerSostiene.inserisci(l);
		}
	}

	public void inserisciPerManagerSostiene(ManagerSostiene m) {
		if (m != null) {
			condominio = m.getLink();
		}
	}

	public void eliminaLinkSostiene(TipoLinkSostiene l) {
		if (l != null && l.getSpesa() == this) {
			ManagerSostiene.elimina(l);
		}
	}

	public void eliminaPerManagerSostiene(ManagerSostiene m) {
		if (m != null) {
			condominio = null;
		}
	}

	public TipoLinkSostiene getLinkSostiene() throws EccezioneMoltMinMax {
		if (condominio == null)
			throw new EccezioneMoltMinMax("Cardinalita' min/max violata");
		return condominio;
	}

	public int quantiCondomini() {
		if (condominio == null)
			return 0;
		return 1;
	}


}
