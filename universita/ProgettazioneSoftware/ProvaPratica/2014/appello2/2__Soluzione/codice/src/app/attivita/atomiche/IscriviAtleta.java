package app.attivita.atomiche;

import app._framework.*;
import app.dominio.*;

public class IscriviAtleta implements Task {

	private boolean eseguita = false;
	private Gara gara;
	private Atleta atleta;

	public IscriviAtleta(Gara gara, Atleta atleta) {
		this.gara = gara;
		this.atleta = atleta;
	}

	public synchronized void esegui(Executor e) {
		if (e == null || eseguita == true)
			return;
		eseguita = true;

		TipoLinkPartecipa link = null;
		try {
			link = new TipoLinkPartecipa(gara, atleta, 0);
		} catch (EccezionePrecondizioni eccezione) {
			eccezione.printStackTrace();
			System.exit(1);
		}
		gara.inserisciLinkPartecipa(link);
	}

	public synchronized boolean estEseguita() {
		return eseguita;
	}
}
