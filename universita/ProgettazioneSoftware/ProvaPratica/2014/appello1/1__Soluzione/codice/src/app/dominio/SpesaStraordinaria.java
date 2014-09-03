package app.dominio;

import java.util.Date;

public class SpesaStraordinaria extends Spesa {

	private final Date dataApprovazione;

	public SpesaStraordinaria(String codice, double importo, int anno, String descrizione, Date dataApprovazione) {
		super(codice, importo, anno, descrizione);
		this.dataApprovazione = dataApprovazione;
	}



	public Date getDataApprovazione() {
		return dataApprovazione;
	}
	
}
