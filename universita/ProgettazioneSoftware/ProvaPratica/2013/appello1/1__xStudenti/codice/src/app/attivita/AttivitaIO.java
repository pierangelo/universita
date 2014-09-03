package app.attivita;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import app.dominio.Attracco;
import app.dominio.Battello;
import app.dominio.Tratta;
import app.gui.BattelloView;
import app.gui.ErrorNotifier;
import app.gui.FinestraDatiAttracco;
import app.gui.FinestraDatiBattello;
import app.gui.FinestraDatiTratta;

public class AttivitaIO {
  
  private AttivitaIO() {}
  
  // Dati battello
  public static Battello inserisciDatiBattello() {
    TargetBattello target = new TargetBattello();
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
    FinestraDatiBattello finestra = target.finestra;
    finestra.aspettaOK();
    Battello battello = new Battello(finestra.leggiNomeBattello(), finestra.leggiPostiBattello(), finestra.leggiLunghezzaBattello(), finestra.leggiProfonditaBattello());
    return battello;
  }
  
  // Dati tratta
  public static Tratta inserisciDatiTratta() {
    TargetTratta target = new TargetTratta();
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
    FinestraDatiTratta finestra = target.finestra;
    finestra.aspettaOK();
    Tratta tratta = new Tratta(finestra.leggiNomeTratta(), finestra.leggiMinProfondita(), finestra.leggiMaxProfondita());
    return tratta;
  }
  
  public static void mostraErrore(String msg) {
    ErrorNotifier.notifyError(msg);
  }
  
  // Dati attracco
  public static RecordDatiAttracco inserisciDatiAttracco() {
    TargetAttracco target = new TargetAttracco();
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
    FinestraDatiAttracco finestra = target.finestra;
    finestra.aspettaOK();
    
    Attracco attracco = new Attracco(finestra.leggiNomeAttracco(), finestra.leggiLunghezza());
    
    RecordDatiAttracco record = new RecordDatiAttracco(attracco, finestra.leggiDistanza());
    return record;
  }
  
  // Altro attracco
  public static boolean chiediSeAltroAttracco() {
    int yn = JOptionPane.showConfirmDialog(null,
        "Aggiungere un altro attracco?",
        "Domanda", JOptionPane.YES_NO_OPTION);
    return (yn == JOptionPane.YES_OPTION);
  }
  
  // Simulazione e grafica
  public static void visualizzaSimulazione(Battello battello) {
    TargetSimul target = new TargetSimul(battello);
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
    BattelloView finestra = target.finestra;
    finestra.aspettaOK();
  }
  
  // Altra simulazione
  public static boolean chiediSeAltraSimulazione() {
    int yn = JOptionPane.showConfirmDialog(null,
        "Eseguire una nuova simulazione?",
        "Domanda", JOptionPane.YES_NO_OPTION);
    return (yn == JOptionPane.YES_OPTION);
  }
  
  
  
  // Elementi ausiliari
  private static class TargetBattello implements Runnable {

    private FinestraDatiBattello finestra;

    @Override
    public void run() {
      finestra = new FinestraDatiBattello();
    }
  }
  
  private static class TargetTratta implements Runnable {

    private FinestraDatiTratta finestra;

    @Override
    public void run() {
      finestra = new FinestraDatiTratta();
    }
  }
  
  private static class TargetAttracco implements Runnable {

    private FinestraDatiAttracco finestra;

    @Override
    public void run() {
      finestra = new FinestraDatiAttracco();
    }
  }
  
  private static class TargetSimul implements Runnable {

    private BattelloView finestra;
    private Battello battello;
    
    public TargetSimul(Battello t) {
      battello = t;
    }

    @Override
    public void run() {
      finestra = new BattelloView(battello);
    }
  }

}
