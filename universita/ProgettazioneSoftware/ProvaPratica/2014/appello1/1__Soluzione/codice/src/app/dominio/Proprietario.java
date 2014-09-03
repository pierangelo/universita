package app.dominio;

import java.util.HashSet;
import java.util.Set;

public class Proprietario {

	private final String nome;
	private final String cognome;
	private final String codiceFiscale;

	private HashSet<String> numTelefono;

	private HashSet<TipoLinkPossiede> insiemeLinkPossiede;

	public static final int MIN_POSSIEDE = 1;

	public Proprietario(String nome, String cognome, String codiceFiscale) {
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		insiemeLinkPossiede = new HashSet<TipoLinkPossiede>();
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void aggiungiNumTelefono(String numero) {
		if (numero != null)
			numTelefono.add(numero);
	}
	
	public void eliminaNumTelefono(String numero) {
		numTelefono.remove(numero);
	}
	
	public Set<String> getNumeriTelefono() {
		return (HashSet<String>)numTelefono.clone();
	}

	// Associazione Possiede
	public void inserisciLinkPossiede(TipoLinkPossiede l) {
		if (l != null && l.getProprietario() == this) {
			ManagerPossiede.inserisci(l);
		}
	}

	public void inserisciPerManagerPossiede(ManagerPossiede m) {
		if (m != null) {
			insiemeLinkPossiede.add(m.getLink());
		}
	}

	public void eliminaLinkPossiede(TipoLinkPossiede l) {
		if (l != null && l.getProprietario() == this) {
			ManagerPossiede.elimina(l);
		}
	}

	public void eliminaPerManagerPossiede(ManagerPossiede m) {
		if (m != null) {
			insiemeLinkPossiede.remove(m.getLink());
		}
	}

	public Set<TipoLinkPossiede> getLinkPossiede() throws EccezioneMoltMinMax {
		if (quantiImmobili() < MIN_POSSIEDE)
			throw new EccezioneMoltMinMax("Cardinalita' minima violata");
		return (HashSet<TipoLinkPossiede>) insiemeLinkPossiede.clone();
	}

	public int quantiImmobili() {
		return insiemeLinkPossiede.size();
	}
}
