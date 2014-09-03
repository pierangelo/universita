package app.attivita.atomiche;

import app._framework.Executor;
import app._framework.Task;
import app.dominio.Atleta;
import app.dominio.EccezionePrecondizioni;
import app.dominio.Tavola;
import app.dominio.TipoLinkPossiede;

public class AssegnaTavola implements Task {

	private boolean eseguita = false;
	private Atleta atleta;
	private Tavola tavola;

	public AssegnaTavola(Atleta atleta, Tavola tavola) {
		this.atleta = atleta;
		this.tavola = tavola;
	}

	public synchronized void esegui(Executor e) {
		if (e == null || eseguita == true)
			return;
		eseguita = true;
		
		TipoLinkPossiede link = null;
		try {
			link = new TipoLinkPossiede(atleta, tavola);
		} catch (EccezionePrecondizioni e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		atleta.inserisciLinkPossiede(link);
		
	}

	public synchronized boolean estEseguita() {
		return eseguita;
	}
}
