package app.attivita.complesse;

import app._framework.Executor;
import app.attivita.atomiche.CalcolaSpeseOrdinarie;
import app.dominio.Condominio;

public class AttivitaSottoramo1_1 implements Runnable {

	private boolean eseguita = false;
	private Condominio condominio;
	private int anno;

	private double speseOrdinarie;

	public AttivitaSottoramo1_1(Condominio condominio, int anno) {
		this.condominio = condominio;
		this.anno = anno;
	}

	@Override
	public void run() {
		if (eseguita == true)
			return;
		eseguita = true;
		
		CalcolaSpeseOrdinarie cso = new CalcolaSpeseOrdinarie(condominio, anno);
		Executor.perform(cso);
		speseOrdinarie = cso.getRisultato();
		
	}
	
	public synchronized double getRisultato() {
		if (!eseguita)
			throw new RuntimeException("Attivita' non eseguita!");
		return speseOrdinarie;
	}



}
