package app.attivita.atomiche;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import app._framework.Executor;
import app._framework.Task;
import app.dominio.Condominio;
import app.dominio.EccezioneMoltMinMax;
import app.dominio.Immobile;
import app.dominio.TipoLinkInclude;

public class RipartisciSpesa implements Task {
	
	private boolean eseguita = false;
	
	private Condominio condominio;
	private double spesaTotale;
	private Map<Immobile, Double> result;
	
	public RipartisciSpesa(Condominio condominio, double spesa) {
		this.condominio = condominio;
		spesaTotale = spesa;
		result = new HashMap<Immobile, Double>();
	}
	
	@Override
	public void esegui(Executor e) {
		if (e == null || eseguita == true)
			return;
		eseguita = true;
		
		/* DA COMPLETARE A CURA DELLO STUDENTE */
		
		
		
		
		
	}
	
	public synchronized Map<Immobile, Double> getRisultato() {
		if (!eseguita)
			throw new RuntimeException("Attivita' non eseguita!");
		return result;
	}
	
	

}
