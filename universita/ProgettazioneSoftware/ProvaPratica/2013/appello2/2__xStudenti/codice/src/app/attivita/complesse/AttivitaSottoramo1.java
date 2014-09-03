package app.attivita.complesse;

import app._framework.Executor;
import app.attivita.AttivitaIO;
import app.attivita.atomiche.*;
import app.dominio.*;

public class AttivitaSottoramo1 implements Runnable {

	private boolean eseguita = false;
	
	private Ordine ordine;
	private Fattura fattura;

	public AttivitaSottoramo1(Ordine o) {
		this.ordine = o;
	}

	public synchronized void run() {
		if (eseguita == true)
			return;
		eseguita = true;

		/* DA COMPLETARE A CURA DELLO STUDENTE */
		
		
		
	}

	public synchronized boolean estEseguita() {
		return eseguita;
	}
}
