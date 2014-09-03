package app.attivita.complesse;

import app.attivita.AttivitaIO;
import app.dominio.Ordine;

public class AttivitaSottoramo2 implements Runnable {

	private boolean eseguita = false;
	
	private Ordine ordine;
	
	public AttivitaSottoramo2(Ordine o) {
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
