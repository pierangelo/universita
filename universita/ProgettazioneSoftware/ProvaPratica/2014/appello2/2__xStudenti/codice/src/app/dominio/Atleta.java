package app.dominio;

import java.util.Observable;
import java.util.Random;

import app._framework.*;
import app._gestioneeventi.*;

public class Atleta extends Observable implements Listener {

	private final String nome;
	private final String cognome;
	private String nazione;

	private TipoLinkPartecipa linkPartecipa;
	private TipoLinkVince linkVince;
	private TipoLinkPossiede linkPossiede;
	
	
	private final static String[] nomi = {"Alessandro", "Mario", "Francesco", "Andrea", "Riccardo", "Marco", "Massimo", "Paolo", "Claudio", "Giuseppe", "Maurizio"};
	private final static String[] cognomi = {"Rossi", "Gialli", "Verdi", "Neri", "Bianchi", "Alti", "Bassi", "Buoni", "Bravi", "Pini", "Chiari"};
	public final static  String[] naz = {"ITA", "FRA", "GER", "BRA", "GBR", "USA", "CAN"};

	public Atleta(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public String getNazione() {
		return nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	public String getCognome() {
		return cognome;
	}


	// Associazione partecipa
	public void inserisciLinkPartecipa(TipoLinkPartecipa l) {
		if (l != null && l.getAtleta() == this) {
			ManagerPartecipa.inserisci(l);
		}
	}

	public void inserisciPerManagerPartecipa(ManagerPartecipa m) {
		if (m != null) {
			linkPartecipa = m.getLink();
			this.setChanged();
			this.notifyObservers(pos);
		}
	}

	public void eliminaLinkPartecipa(TipoLinkPartecipa l) {
		if (l != null && l.getAtleta() == this) {
			ManagerPartecipa.elimina(l);
		}
	}

	public void eliminaPerManagerPartecipa(ManagerPartecipa m) {
		if (m != null) {
			linkPartecipa = null;
		}
	}

	public TipoLinkPartecipa getLinkPartecipa() {
		return linkPartecipa;
	}

	public int quanteGare() {
		if (linkPartecipa != null) {
			return 1;
		}
		return 0;
	}

	// Associazione vince
	public void inserisciLinkVince(TipoLinkVince l) {
		if (l != null && l.getAtleta() == this) {
			ManagerVince.inserisci(l);
		}
	}

	public void inserisciPerManagerVince(ManagerVince m) {
		if (m != null) {
			linkVince = m.getLink();
		}
	}

	public void eliminaLinkVince(TipoLinkVince l) {
		if (l != null && l.getAtleta() == this) {
			ManagerVince.elimina(l);
		}
	}

	public void eliminaPerManagerVince(ManagerVince m) {
		if (m != null) {
			linkVince = null;
		}
	}

	public TipoLinkVince getLinkVince() throws EccezioneSubset {
		if (linkVince != null) {
			try {
				if (linkPartecipa != new TipoLinkPartecipa(linkVince.getGara(), this, 0))
					throw new EccezioneSubset("Vincolo di subset violato");
			} catch (EccezionePrecondizioni e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return linkVince;
	}

	public int quanteGareVinte() {
		if (linkVince != null) {
			return 1;
		}
		return 0;
	}


	// Associazione possiede
	public void inserisciLinkPossiede(TipoLinkPossiede l) {
		if (l != null && l.getAtleta() == this) {
			ManagerPossiede.inserisci(l);
		}
	}

	public void inserisciPerManagerPossiede(ManagerPossiede m) {
		if (m != null) {
			linkPossiede = m.getLink();
		}
	}

	public void eliminaLinkPossiede(TipoLinkPossiede l) {
		if (l != null && l.getAtleta() == this) {
			ManagerPossiede.elimina(l);
		}
	}

	public void eliminaPerManagerPossiede(ManagerPossiede m) {
		if (m != null) {
			linkPossiede = null;
		}
	}

	public TipoLinkPossiede getLinkPossiede() throws EccezioneMoltMinMax {
		if (linkPossiede == null) {
			throw new EccezioneMoltMinMax("Violazione cardinalita'!");
		}
		return linkPossiede;
	}

	public int quanteTavole() {
		if (linkPossiede != null) {
			return 1;
		}
		return 0;
	}


	// gestione stato

	public static enum Stato {
		PRONTO, IN_VOLO, FINITO
	}

	// variabile di stato
	Stato statoCorrente = Stato.PRONTO;
	
	// variabili di stato ausiliarie
	Posizione pos = new Posizione(0, 0); // posizione (x,y) dell'atleta nel percorso di salto
	
	// paramteri per il percorso di salto...semplice moto parabolico secondo l'equazione
	// y = ax^2 + b^x + c
	// i parametri sono inizializzati alla ricezione dell'evento "Start" (vedi AtletaFired)
	double a;
	double b;
	double c;

	
	void notifyView(Object updt) {
		this.setChanged();
		this.notifyObservers(updt);
	}

	public Stato getStato() {
		return statoCorrente;
	}

	public void fired(Evento e) {
		Executor.perform(new AtletaFired(this, e));
	}
	
	
	
	
	
	public static Atleta getRandomAtleta() {
		Atleta atleta = new Atleta(nomi[randInt(0, nomi.length-1)], cognomi[randInt(0, cognomi.length-1)]);
		atleta.setNazione(naz[randInt(0, naz.length-1)]);
		return atleta;
	}
	
	private static int randInt(int min, int max) {

	    // Usually this should be a field rather than a method variable so
	    // that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
}
