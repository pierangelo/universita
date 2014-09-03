package app.attivita;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import app.dominio.Tavolo;
import app.dominio.Fattura;
import app.dominio.Ordine;
import app.gui.ErrorNotifier;
import app.gui.FinestraFattura;
import app.gui.FinestraProdotto;
import app.gui.FinestraStampaOrdine;

public class AttivitaIO {

  private AttivitaIO() {}

  // Selezione Tavolo
  public static Tavolo selezionaTavolo() {
    
    Object[] tavoli = Tavolo.getTavoliDefault();
    Object selectedValue = null;
    do {
    selectedValue = JOptionPane.showInputDialog(null,

            "Seleziona il tavolo", "Tavolo",

            JOptionPane.INFORMATION_MESSAGE, null,

            tavoli, tavoli[0]);
    } while(selectedValue == null);
    
    return (Tavolo)selectedValue;
  }
  
  
  //Selezione Prodotto
  public static RecordSelezioneProdotto selezionaProdotto() {
    TargetProdotto target = new TargetProdotto();
    try {
      SwingUtilities.invokeAndWait(target);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      System.exit(1);
    } catch (InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      System.exit(1);
    }
    FinestraProdotto finestra = target.finestra;
    finestra.aspettaOK();
    return new RecordSelezioneProdotto(finestra.getProdottoSelezionato(),finestra.getQuantita());
  }
  
  // Altro prodotto
  public static boolean chiediSeAltroProdotto() {
    int yn = JOptionPane.showConfirmDialog(null,
        "Aggiungere un altro prodotto?",
        "Domanda", JOptionPane.YES_NO_OPTION);
    return (yn == JOptionPane.YES_OPTION);
  }
  
  //Nuovo ordine
  public static boolean chiediSeNuovoOrdine() {
    int yn = JOptionPane.showConfirmDialog(null,
        "Procedere con un nuovo ordine?",
        "Domanda", JOptionPane.YES_NO_OPTION);
    return (yn == JOptionPane.YES_OPTION);
  }
  
  //Stampa fattura
  public static void stampaFattura(Fattura f) {
    TargetFattura target = new TargetFattura(f);
    try {
      SwingUtilities.invokeAndWait(target);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      System.exit(1);
    } catch (InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      System.exit(1);
    }
    FinestraFattura finestra = target.finestra;
    finestra.aspettaOK();
  }
  
//Stampa fattura
  public static void stampaOrdine(Ordine o) {
    TargetStampaOrdine target = new TargetStampaOrdine(o);
    try {
      SwingUtilities.invokeAndWait(target);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      System.exit(1);
    } catch (InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      System.exit(1);
    }
    FinestraStampaOrdine finestra = target.finestra;
    finestra.aspettaOK();
  }
  
  public static void MostraErrore() {
    ErrorNotifier.notifyError("Un ordine deve contenere un numero di bevande\nalmeno pari al numeri di posti del tavolo!");
  }
  

  private static class TargetProdotto implements Runnable {

    private FinestraProdotto finestra;

    @Override
    public void run() {
      finestra = new FinestraProdotto();
    }
  }

  private static class TargetFattura implements Runnable {

    private FinestraFattura finestra;
    private Fattura fattura;

    public TargetFattura(Fattura f) {
      this.fattura = f;
    }

    @Override
    public void run() {
      finestra = new FinestraFattura(fattura);
    }
  }
  
  private static class TargetStampaOrdine implements Runnable {

    private FinestraStampaOrdine finestra;
    private Ordine ordine;

    public TargetStampaOrdine(Ordine o) {
      this.ordine = o;
    }

    @Override
    public void run() {
      finestra = new FinestraStampaOrdine(ordine);
    }
  }

}
