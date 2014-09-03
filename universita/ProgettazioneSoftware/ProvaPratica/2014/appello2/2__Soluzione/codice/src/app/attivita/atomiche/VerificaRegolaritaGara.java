package app.attivita.atomiche;

import app._framework.Executor;
import app._framework.Task;
import app.dominio.Gara;

public class VerificaRegolaritaGara implements Task {

	private boolean eseguita = false;
	private Gara gara;
	private boolean result;

	public VerificaRegolaritaGara(Gara gara) {
		this.gara = gara;
	}

	public synchronized void esegui(Executor e) {
		if (e == null || eseguita == true)
			return;
		eseguita = true;
		
		result = gara.quantiAtleti() >= 2 && gara.quantiAtleti() <= 6;
		
	}

	public synchronized boolean estEseguita() {
		return eseguita;
	}
	
	public synchronized boolean getRisultato() {
		if (!eseguita)
			throw new RuntimeException("Attivita' non eseguita!");
		return result;
	}
}
