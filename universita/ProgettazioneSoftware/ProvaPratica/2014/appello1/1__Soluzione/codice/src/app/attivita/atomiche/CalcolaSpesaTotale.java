package app.attivita.atomiche;

import app._framework.Executor;
import app._framework.Task;

public class CalcolaSpesaTotale implements Task {

	private boolean eseguita = false;
	private double speseOrdinarie;
	private double speseStraordinarie;
	
	private double result;

	public CalcolaSpesaTotale(double ordinarie, double straordinarie) {
		speseOrdinarie = ordinarie;
		speseStraordinarie = straordinarie;
	}
	
	@Override
	public void esegui(Executor e) {
		if (e == null || eseguita == true)
			return;
		eseguita = true;
		
		result = speseOrdinarie + speseStraordinarie;
		
	}
	
	public synchronized double getRisultato() {
		if (!eseguita)
			throw new RuntimeException("Attivita' non eseguita!");
		return result;
	}

}
