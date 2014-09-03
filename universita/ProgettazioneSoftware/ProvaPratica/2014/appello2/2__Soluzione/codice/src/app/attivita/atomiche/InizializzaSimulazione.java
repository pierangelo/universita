package app.attivita.atomiche;

import app._framework.Executor;
import app._framework.Task;
import app._gestioneeventi.EsecuzioneEnvironment;

import app.dominio.Atleta;
import app.dominio.EccezioneMoltMinMax;
import app.dominio.Gara;
import app.dominio.TipoLinkPartecipa;

public class InizializzaSimulazione implements Task {

	private boolean eseguita = false;
	private Gara gara;

	public InizializzaSimulazione(Gara gara) {
		this.gara = gara;
	}

	public synchronized void esegui(Executor e) {
		if (e == null || eseguita == true)
			return;
		eseguita = true;

		// Inizializza environment
		try {
			Atleta atleta;
			for (TipoLinkPartecipa link : gara.getLinkPartecipa()) {
				atleta = link.getAtleta();
				EsecuzioneEnvironment.addListener(atleta);
			}
		} catch (EccezioneMoltMinMax e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Attiva listener
		EsecuzioneEnvironment.attivaListener();

	}

	public synchronized boolean estEseguita() {
		return eseguita;
	}

}
