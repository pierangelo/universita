package app.attivita.complesse;

import java.util.Map;
import java.util.Set;

import app.attivita.AttivitaIO;
import app.dominio.*;

public class AttivitaPrincipale implements Runnable {
  
  private boolean eseguita = false;
  
  private Set<Condominio> insiemeCondomini;
  
  private Condominio condominio;
  private int anno;
  private double spesaOrdinaria;
  private double spesaStraordinaria;
  private Map<Immobile, Double> ripartizioneSpesa;
  
  
  public AttivitaPrincipale(Set<Condominio> condomini) {
	  insiemeCondomini = condomini;
  }

  public synchronized void run() {
    
    if (eseguita == true)
      return;
    eseguita = true;

    condominio = AttivitaIO.selezionaCondominio(insiemeCondomini);
    
    anno = AttivitaIO.selezionaAnno(condominio);
    
    AttivitaSottoramo1_1 a1_1 = new AttivitaSottoramo1_1(condominio, anno);
    Thread threadSottoramo1_1 = new Thread(a1_1);
    
    AttivitaSottoramo2_1 a2_1 = new AttivitaSottoramo2_1(condominio, anno);
    Thread threadSottoramo2_1 = new Thread(a2_1);
    
    threadSottoramo1_1.start();
    threadSottoramo2_1.start();
    
    try {
		threadSottoramo1_1.join();
		threadSottoramo2_1.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    spesaOrdinaria = a1_1.getRisultato();
    spesaStraordinaria = a2_1.getRisultato();
    
    AttivitaIO.visualizzaSpese(spesaOrdinaria, spesaStraordinaria);
    
    if (spesaStraordinaria > spesaOrdinaria) {
    	AttivitaIO.mostraNotifica();
    }
    
    AttivitaSottoramo1_2 a1_2 = new AttivitaSottoramo1_2(condominio, spesaOrdinaria, spesaStraordinaria);
    Thread threadSottoramo1_2 = new Thread(a1_2);
    
    AttivitaSottoramo2_2 a2_2 = new AttivitaSottoramo2_2(condominio);
    Thread threadSottoramo2_2 = new Thread(a2_2);
    
    threadSottoramo1_2.start();
    threadSottoramo2_2.start();
    
    try {
		threadSottoramo1_2.join();
		threadSottoramo2_2.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    ripartizioneSpesa = a1_2.getRisultato();
    
    AttivitaIO.visualizzaRipartizione(ripartizioneSpesa);
    
  }

  
  public synchronized boolean estEseguita() {
    return eseguita;
  }
}
