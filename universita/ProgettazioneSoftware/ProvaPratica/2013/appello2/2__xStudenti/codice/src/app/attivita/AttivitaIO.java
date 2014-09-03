package app.attivita;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import app.dominio.Cliente;
import app.dominio.Fattura;
import app.dominio.Ordine;
import app.gui.ErrorNotifier;
import app.gui.FinestraDatiCliente;
import app.gui.FinestraDatiOrdine;
import app.gui.FinestraFattura;
import app.gui.FinestraProdotto;
import app.gui.FinestraStampaOrdine;

public class AttivitaIO {

  private AttivitaIO() {}

  // Dati Cliente
  public static Cliente inserisciDatiCliente() {
    TargetCliente target = new TargetCliente();
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
    FinestraDatiCliente finestra = target.finestra;
    finestra.aspettaOK();
    Cliente cliente = new Cliente(finestra.leggiNomeCliente(), finestra.leggiCognomeCliente(), finestra.leggiIndirizzoCliente());
    return cliente;
  }

  
  //Dati Ordine
  public static Ordine inserisciDatiOrdine() {
    TargetOrdine target = new TargetOrdine();
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
    FinestraDatiOrdine finestra = target.finestra;
    finestra.aspettaOK();
    Ordine ordine = new Ordine(finestra.leggiIdOrdine(), finestra.leggiDataOrdine(), finestra.leggiConsegnaAdomicilio());
    return ordine;
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
    ErrorNotifier.notifyError("Un ordine deve contenere almeno\nuna pizza e una bibita!");
  }
  

  
  private static class TargetCliente implements Runnable {

    private FinestraDatiCliente finestra;

    @Override
    public void run() {
      finestra = new FinestraDatiCliente();
    }
  }
  
  private static class TargetOrdine implements Runnable {

    private FinestraDatiOrdine finestra;

    @Override
    public void run() {
      finestra = new FinestraDatiOrdine();
    }
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
