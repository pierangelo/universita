package app.attivita.atomiche;

import app._framework.Executor;
import app._framework.Task;
import app.dominio.Gara;
import app.dominio.Percorso;

public class RegistraPercorso implements Task {

	private boolean eseguita = false;
	private Gara gara;
	private Percorso percorso;

	public RegistraPercorso(Gara gara, Percorso percorso) {
		this.gara = gara;
		this.percorso = percorso;
	}

	public synchronized void esegui(Executor e) {
		if (e == null || eseguita == true)
			return;
		eseguita = true;
		
		/* DA COMPLETARE A CURA DELLO STUDENTE */
		
		
		
		
	}

	public synchronized boolean estEseguita() {
		return eseguita;
	}
}
