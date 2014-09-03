package app.dominio;

import java.util.*;

public class Regata {
  
  private final String nome;
  private final float distanza;
  
  private HashSet<Equipaggio> vincitori;
  private HashSet<TipoLinkPartecipa> partecipa;
  
  public final int MIN_PARTECIPA = 2;
  public final int MIN_VINCITORI = 1;

  public Regata(String nome, float distanza) {
    this.nome = nome;
    this.distanza = distanza;
    partecipa = new HashSet<TipoLinkPartecipa>();
    vincitori = new HashSet<Equipaggio>();
  }

  public String getNome() {
    return nome;
  }

  public float getDistanza() {
    return distanza;
  }

  public void inserisciLinkPartecipa(TipoLinkPartecipa l) {
    if (l != null && l.getRegata() == this) {
      ManagerPartecipa.inserisci(l);
    }
  }

  public synchronized void inserisciPerManagerPartecipa(ManagerPartecipa m) {
    if (m != null) {
      partecipa.add(m.getLink());
    }
  }

  public void eliminaLinkPartecipa(TipoLinkPartecipa l) {
    if (l != null && l.getRegata() == this) {
      ManagerPartecipa.elimina(l);
    }
  }

  public void eliminaPerManagerPartecipa(ManagerPartecipa m) {
    if (m != null) {
      partecipa.remove(m.getLink());
    }
  }

  @SuppressWarnings("unchecked")
  public synchronized Set<TipoLinkPartecipa> getLinkPartecipa()
      throws EccezioneMoltMinMax {
    if (quantiEquipaggi() < MIN_PARTECIPA)
      throw new EccezioneMoltMinMax("Eccezione Molteplicita' minima");
    return (HashSet<TipoLinkPartecipa>) partecipa.clone();
  }

  public int quantiEquipaggi() {
    return partecipa.size();
  }

  public void inserisciLinkVincitore(Equipaggio c) {
    if (c != null) {
      vincitori.add(c);
    }
  }

  public void eliminaLinkVincitore(Equipaggio c) {
    if (c != null) {
      vincitori.remove(c);
    }
  }

  @SuppressWarnings("unchecked")
  public Set<Equipaggio> getLinkVincitori() throws EccezioneMoltMinMax, EccezioneSubset {
    if (quantiVincitori() < MIN_VINCITORI) {
      throw new EccezioneMoltMinMax("Eccezione Molteplicita' minima");
    }
    // Verifica del vincolo di Subset
    Iterator<Equipaggio> it = vincitori.iterator();
    while (it.hasNext()) {
      try {
        if (!partecipa.contains(new TipoLinkPartecipa(this, it.next(), 0))) {
          throw new EccezioneSubset("Vincolo di subset violato");
        }
      } catch (EccezionePrecondizioni eccezione) {
        eccezione.printStackTrace();
        System.exit(1);
      }
    }
    // Restituisce i vincitori
    return (HashSet<Equipaggio>) vincitori.clone();
  }

  public int quantiVincitori() {
    return vincitori.size();
  }
}
