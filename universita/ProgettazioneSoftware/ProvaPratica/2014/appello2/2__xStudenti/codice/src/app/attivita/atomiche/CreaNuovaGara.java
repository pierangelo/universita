package app.attivita.atomiche;

import java.util.Calendar;
import java.util.UUID;

import app._framework.Executor;
import app._framework.Task;
import app.dominio.Gara;

public class CreaNuovaGara implements Task {

	private boolean eseguita = false;
	private Gara result;

	public CreaNuovaGara() {
	}

	public synchronized void esegui(Executor e) {
		if (e == null || eseguita == true)
			return;
		eseguita = true;
		
		result = new Gara(UUID.randomUUID().toString(), Calendar.getInstance().getTime());
		
	}

	public synchronized boolean estEseguita() {
		return eseguita;
	}
	
	public synchronized Gara getRisultato() {
		if (!eseguita)
			throw new RuntimeException("Attivita' non eseguita!");
		return result;
	}
}
