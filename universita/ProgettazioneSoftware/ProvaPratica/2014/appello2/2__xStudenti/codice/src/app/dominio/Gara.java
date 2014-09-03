package app.dominio;

import java.util.*;

public class Gara {

	private final String codice;
	private final Date data;

	private HashSet<TipoLinkPartecipa> linkPartecipa;
	public final int MIN_PARTECIPA = 2;
	public final int MAX_PARTECIPA = 6;

	private HashSet<TipoLinkVince> linkVince;
	public final int MIN_VINCE = 1;
	public final int MAX_VINCE = MAX_PARTECIPA;
	
	private Percorso percorso;

	public Gara(String nome, Date data) {
		this.codice = nome;
		this.data = data;
		linkPartecipa = new HashSet<TipoLinkPartecipa>();
		linkVince = new HashSet<TipoLinkVince>();
	}

	public String getCodice() {
		return codice;
	}

	public Date getData() {
		return data;
	}


	// Associazione partecipa
	public void inserisciLinkPartecipa(TipoLinkPartecipa l) {
		if (l != null && l.getGara() == this) {
			ManagerPartecipa.inserisci(l);
		}
	}

	public void inserisciPerManagerPartecipa(ManagerPartecipa m) {
		if (m != null) {
			linkPartecipa.add(m.getLink());
		}
	}

	public void eliminaLinkPartecipa(TipoLinkPartecipa l) {
		if (l != null && l.getGara() == this) {
			ManagerPartecipa.elimina(l);
		}
	}

	public void eliminaPerManagerPartecipa(ManagerPartecipa m) {
		if (m != null) {
			linkPartecipa.remove(m.getLink());
		}
	}

	@SuppressWarnings("unchecked")
	public Set<TipoLinkPartecipa> getLinkPartecipa() throws EccezioneMoltMinMax {
		/* DA COMPLETARE A CURA DELLO STUDENTE */
		// eliminare l'istruzione 'return null;'
		
		
		
		return null;
	}

	public int quantiAtleti() {
		return linkPartecipa.size();
	}


	//Associazione vince
	public void inserisciLinkVince(TipoLinkVince l) {
		if (l != null && l.getGara() == this) {
			ManagerVince.inserisci(l);
		}
	}

	public void inserisciPerManagerVince(ManagerVince m) {
		if (m != null) {
			linkVince.add(m.getLink());
		}
	}

	public void eliminaLinkVince(TipoLinkVince l) {
		if (l != null && l.getGara() == this) {
			ManagerVince.elimina(l);
		}
	}

	public void eliminaPerManagerVince(ManagerVince m) {
		if (m != null) {
			linkVince.remove(m.getLink());
		}
	}

	@SuppressWarnings("unchecked")
	public Set<TipoLinkVince> getLinkVince() throws EccezioneMoltMinMax, EccezioneSubset {
		// molteplicita'
		if (quantiVincitori() < MIN_VINCE || quantiVincitori() > MAX_VINCE) {
			throw new EccezioneMoltMinMax("Molteplicita' minima o massima violata!");
		}
		// controllo subset
		for (TipoLinkVince link : linkVince) {
			try {
				if (!linkPartecipa.contains(new TipoLinkPartecipa(this, link.getAtleta(), 0))) {
					throw new EccezioneSubset("Vincolo di subset violato");
				}
			} catch (EccezionePrecondizioni e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (HashSet<TipoLinkVince>) linkVince.clone();
	}

	public int quantiVincitori() {
		return linkVince.size();
	}
	
	public void setPercorso(Percorso percorso) {
		if (this.percorso == null)
			this.percorso = percorso; 
	}
	
	public void eliminaPercorso() {
		percorso = null;
	}
	
	public Percorso getPercorso() throws EccezioneMoltMinMax {
		if (percorso == null)
			throw new EccezioneMoltMinMax("Molteplicita' violata!");
		return percorso;
	}
	
	public int quantiPercorsi() {
		if (percorso == null)
			return 0;
		return 1;
	}
}
