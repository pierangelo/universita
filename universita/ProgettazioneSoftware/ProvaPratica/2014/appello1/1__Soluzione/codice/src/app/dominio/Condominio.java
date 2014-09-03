package app.dominio;

import java.util.HashSet;
import java.util.Set;

public class Condominio {

	private final String nome;
	private final String indirizzo;
	
	private HashSet<TipoLinkInclude> insiemeLinkInclude;
	private HashSet<TipoLinkSostiene> insiemeLinkSostiene;
	
	public final static int MIN_INCLUDE = 2;
	public final static int MIN_SOSTIENE = 1;

	public Condominio(String nome, String indirizzo) {
		this.nome = nome;
		this.indirizzo = indirizzo;
		insiemeLinkInclude = new HashSet<TipoLinkInclude>();
		insiemeLinkSostiene = new HashSet<TipoLinkSostiene>();
	}

	public String getNome() {
		return nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	// Associazione Include
	public void inserisciLinkInclude(TipoLinkInclude l) {
		if (l != null && l.getCondominio() == this) {
			ManagerInclude.inserisci(l);
		}
	}

	public void inserisciPerManagerInclude(ManagerInclude m) {
		if (m != null) {
			insiemeLinkInclude.add(m.getLink());
		}
	}

	public void eliminaLinkInclude(TipoLinkInclude l) {
		if (l != null && l.getCondominio() == this) {
			ManagerInclude.elimina(l);
		}
	}

	public void eliminaPerManagerInclude(ManagerInclude m) {
		if (m != null) {
			insiemeLinkInclude.remove(m.getLink());
		}
	}

	@SuppressWarnings("unchecked")
	public Set<TipoLinkInclude> getLinkInclude() throws EccezioneMoltMinMax {
		if (quantiImmobili() < MIN_INCLUDE)
			throw new EccezioneMoltMinMax("Eccezione Molteplicita' minima");
		return (HashSet<TipoLinkInclude>) insiemeLinkInclude.clone();
	}

	public int quantiImmobili() {
		return insiemeLinkInclude.size();
	}
	
	// Associazione Sostiene
	public void inserisciLinkSostiene(TipoLinkSostiene l) {
		if (l != null && l.getCondominio() == this) {
			ManagerSostiene.inserisci(l);
		}
	}

	public void inserisciPerManagerSostiene(ManagerSostiene m) {
		if (m != null) {
			insiemeLinkSostiene.add(m.getLink());
		}
	}

	public void eliminaLinkSostiene(TipoLinkSostiene l) {
		if (l != null && l.getCondominio() == this) {
			ManagerSostiene.elimina(l);
		}
	}

	public void eliminaPerManagerSostiene(ManagerSostiene m) {
		if (m != null) {
			insiemeLinkSostiene.remove(m.getLink());
		}
	}

	@SuppressWarnings("unchecked")
	public Set<TipoLinkSostiene> getLinkSostiene() throws EccezioneMoltMinMax {
		if (quanteSpese() < MIN_SOSTIENE)
			throw new EccezioneMoltMinMax("Eccezione Molteplicita' minima");
		return (HashSet<TipoLinkSostiene>) insiemeLinkSostiene.clone();
	}

	public int quanteSpese() {
		return insiemeLinkSostiene.size();
	}
	
	public String toString() {
		return nome + " - " + indirizzo;
	}

}
