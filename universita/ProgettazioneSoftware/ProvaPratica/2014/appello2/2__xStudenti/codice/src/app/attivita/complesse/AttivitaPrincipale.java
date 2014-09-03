package app.attivita.complesse;

import app._framework.*;
import app._gestioneeventi.EsecuzioneEnvironment;
import app.attivita.AttivitaIO;
import app.attivita.atomiche.*;
import app.dominio.*;

public class AttivitaPrincipale implements Runnable {

	private boolean eseguita = false;

	private Gara gara;
	private Percorso percorso;

	private Atleta atleta;
	private Tavola tavola;
	private boolean altroAtleta;

	private boolean garaRegolare;

	public synchronized void run() {

		if (eseguita == true)
			return;
		eseguita = true;

		/* DA COMPLETARE A CURA DELLO STUDENTE */
		
		
		


		do {

			atleta = AttivitaIO.inserisciDatiAtleta();

			tavola = AttivitaIO.inserisciDatiTavola();

			AssegnaTavola at = new AssegnaTavola(atleta, tavola);
			Executor.perform(at);

			IscriviAtleta ia = new IscriviAtleta(gara, atleta);
			Executor.perform(ia);

			altroAtleta = AttivitaIO.chiediSeAltroAtleta();

		} while(altroAtleta);

		VerificaRegolaritaGara vrg = new VerificaRegolaritaGara(gara);
		Executor.perform(vrg);
		garaRegolare = vrg.getRisultato();

		if (garaRegolare) {
			InizializzaSimulazione is = new InizializzaSimulazione(gara);
			Executor.perform(is);

			AttivitaIO.visualizzaGara(gara);
			
			EsecuzioneEnvironment.disattivaListener();
			
			DeterminaVincitori dv = new DeterminaVincitori(gara);
			Executor.perform(dv);
			
			AttivitaIO.visualizzaRiassuntoGara(gara);


		}
		else {
			AttivitaIO.mostraErrore();
		}
	}


	public synchronized boolean estEseguita() {
		return eseguita;
	}
}
