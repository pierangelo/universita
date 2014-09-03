package app.dominio;

import java.util.*;

import app._framework.*;
import app._gestioneeventi.*;
import app.dominio.Atleta.Stato;
import app.dominio.eventi.*;
import app.gui.PercorsoSalto;


public class AtletaFired implements Task {

	private boolean eseguita = false;

	private Atleta atleta;
	private Evento evento;

	public AtletaFired(Atleta atleta, Evento evento) {
		this.atleta = atleta;
		this.evento = evento;
	}

	public synchronized void esegui(Executor executor) {
		
		if (eseguita || executor == null
				|| (evento.getDestinatario() != atleta && evento.getDestinatario() != null)
				|| atleta.getLinkPartecipa() == null) { // Verifica precondizione
			return;
		}

		eseguita = true;
		
		
		switch (atleta.getStato()) {

		case PRONTO:

			if (evento.getClass() == Start.class) { // pronto -> inGara
				atleta.statoCorrente = Stato.IN_VOLO;
				inizializzaParametriSalto();
				Environment.aggiungiEvento(new Salta(atleta, atleta));
			}
			break;

		case IN_VOLO:

			if (evento.getClass() == Salta.class) {

				if (arrivato()) { // inGara -> finito
					// Cambia stato
					atleta.statoCorrente = Stato.FINITO;
				}
				else { // non e' arrivato
					// Aggiorna i metri percorsi
					aggiornaMtPercorsi();
					Environment.aggiungiEvento(new Salta(atleta, atleta));
				}
			}
			break;

		default:
			throw new RuntimeException("Stato corrente non riconosciuto!");

		}
		atleta.notifyView(atleta.statoCorrente);
	}
	
	
	
	
	private void inizializzaParametriSalto() {
		
		double lunghezza = 0;
		double altezza = 0;
		
		try {
			Gara gara = atleta.getLinkPartecipa().getGara();
			lunghezza = gara.getPercorso().getLunghezza();
			altezza = gara.getPercorso().getAltezza();
		} catch (EccezioneMoltMinMax e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		atleta.c = altezza;
		atleta.b = 0.1;
		double a = ((-atleta.b*lunghezza)-atleta.c) / Math.pow(lunghezza, 2);// + Math.random()/1000;
		
		double max_dist = lunghezza*(PercorsoSalto.LARGHEZZA+PercorsoSalto.LARGHEZZA_AGGIUNTIVA)/PercorsoSalto.LARGHEZZA;
		double a_max = ((-atleta.b*max_dist)-atleta.c) / Math.pow(max_dist, 2);// + Math.random()/1000;
		
		double start = Math.min(a, a_max);
		double end = Math.max(a, a_max);
		double random = new Random().nextDouble();
		double result = start + (random * (end - start));
		
		atleta.a = result;
		atleta.pos.x = 0;
		atleta.pos.y = altezza;
	}

	private void aggiornaMtPercorsi() {
		// Metodo (privato) di servizio
		/*
		 * Aggiorna le variabili di stato ausiliarie dell'atleta
		 */

		double lunghezzaPercorso = 0;
		try {
			lunghezzaPercorso = atleta.getLinkPartecipa().getGara().getPercorso().getLunghezza();
		} catch (EccezioneMoltMinMax e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		double nuovaDistanza = 0;

		nuovaDistanza = atleta.pos.x + (lunghezzaPercorso / 100) * (double) (1 + Math.random());

		atleta.pos.x = nuovaDistanza;
		atleta.pos.y = atleta.a * Math.pow(atleta.pos.x, 2) + atleta.b * atleta.pos.x + atleta.c;

		Gara gara = atleta.getLinkPartecipa().getGara();
	    TipoLinkPartecipa nuovoLink = null;
	    try {
	      nuovoLink = new TipoLinkPartecipa(gara, atleta, nuovaDistanza);
	    } catch (EccezionePrecondizioni e) {
	      e.printStackTrace();
	      System.exit(1);
	    }
	    atleta.eliminaLinkPartecipa(nuovoLink);
	    atleta.inserisciLinkPartecipa(nuovoLink);

	}

	private boolean arrivato() {
		return atleta.pos.y <= 0;
	}

	public synchronized boolean estEseguita() {
		return eseguita;
	}

}
