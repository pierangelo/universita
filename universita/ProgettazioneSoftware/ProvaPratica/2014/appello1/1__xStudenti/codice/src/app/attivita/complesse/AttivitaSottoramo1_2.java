package app.attivita.complesse;

import java.util.Map;

import app._framework.Executor;
import app.attivita.atomiche.CalcolaSpesaTotale;
import app.attivita.atomiche.RipartisciSpesa;
import app.dominio.Condominio;
import app.dominio.Immobile;

public class AttivitaSottoramo1_2 implements Runnable {

	private boolean eseguita = false;
	
	private Condominio condominio;
	private double speseOrdinarie;
	private double speseStraordinarie;
	
	private double spesaTotale;
	
	private Map<Immobile, Double> result;
	
	public AttivitaSottoramo1_2(Condominio condominio, double speseOrdinarie, double speseStraordinarie) {
		this.condominio = condominio;
		this.speseOrdinarie = speseOrdinarie;
		this.speseStraordinarie = speseStraordinarie;
	}

	@Override
	public void run() {
		
		if (eseguita == true)
			return;
		eseguita = true;
		
		CalcolaSpesaTotale cst = new CalcolaSpesaTotale(speseOrdinarie, speseStraordinarie);
	    Executor.perform(cst);
	    spesaTotale = cst.getRisultato();
	    
	    RipartisciSpesa rs = new RipartisciSpesa(condominio, spesaTotale);
	    Executor.perform(rs);
	    result = rs.getRisultato();
		
	}
	
	public synchronized Map<Immobile, Double> getRisultato() {
		if (!eseguita)
			throw new RuntimeException("Attivita' non eseguita!");
		return result;
	}
	
	

}
