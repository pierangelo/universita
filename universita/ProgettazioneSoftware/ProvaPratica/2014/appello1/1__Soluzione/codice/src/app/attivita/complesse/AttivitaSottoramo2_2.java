package app.attivita.complesse;

import java.util.Set;

import app._framework.Executor;
import app.attivita.AttivitaIO;
import app.attivita.atomiche.DeterminaProprietari;
import app.dominio.Condominio;
import app.dominio.Proprietario;

public class AttivitaSottoramo2_2 implements Runnable {

	private boolean eseguita = false;
	
	private Condominio condominio;
	
	private Set<Proprietario> proprietari;

	public AttivitaSottoramo2_2(Condominio condominio) {
		this.condominio = condominio;
	}



	@Override
	public void run() {
		if (eseguita == true)
			return;
		eseguita = true;
		
		DeterminaProprietari dp = new DeterminaProprietari(condominio);
		Executor.perform(dp);
		proprietari = dp.getRisultato();
		
		AttivitaIO.visualizzaProprietari(proprietari);
		
		
	}
	
	

}
