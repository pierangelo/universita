package app.attivita.complesse;

import app._framework.Executor;
import app.attivita.atomiche.CalcolaSpeseStraordinarie;
import app.dominio.Condominio;

public class AttivitaSottoramo2_1 implements Runnable {

	private boolean eseguita = false;
	private Condominio condominio;
	private int anno;

	private double speseStraordinarie;

	public AttivitaSottoramo2_1(Condominio condominio, int anno) {
		this.condominio = condominio;
		this.anno = anno;
	}

	@Override
	public void run() {
		if (eseguita == true)
			return;
		eseguita = true;
		
		CalcolaSpeseStraordinarie css = new CalcolaSpeseStraordinarie(condominio, anno);
		Executor.perform(css);
		speseStraordinarie = css.getRisultato();
		
	}
	
	public synchronized double getRisultato() {
		if (!eseguita)
			throw new RuntimeException("Attivita' non eseguita!");
		return speseStraordinarie;
	}



}
