package app.attivita;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import app.dominio.Semaforo;
import app.dominio.Simulazione;
import app.gui.ErrorNotifier;
import app.gui.FinestraDatiSemaforo;
import app.gui.FinestraDatiSimulazione;
import app.gui.FinestraSimulazione;

public class AttivitaIO {

  private AttivitaIO() {}

  //Dati battello
  public static Simulazione inserisciDatiSimulazione() {
    TargetSimulazione target = new TargetSimulazione();
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
    FinestraDatiSimulazione finestra = target.finestra;
    finestra.aspettaOK();
    Simulazione simulazione = new Simulazione(finestra.leggiCodiceSimulazione(), finestra.leggiDataSimulazione());
    return simulazione;
  }
  
  //Dati semaforo
  public static Semaforo inserisciDatiSemaforo() {
    TargetSemaforo target = new TargetSemaforo();
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
    FinestraDatiSemaforo finestra = target.finestra;
    finestra.aspettaOK();
    Semaforo semaforo = new Semaforo(finestra.leggiNomeSemaforo(), finestra.leggiDurataGiallo(), finestra.leggiDurataVerde());
    return semaforo;
  }

  public static void mostraErrore(String msg) {
    ErrorNotifier.notifyError(msg);
  }

  // Altro semaforo
  public static boolean chiediSeAltroSemaforo() {
    int yn = JOptionPane.showConfirmDialog(null,
        "Aggiungere un altro semaforo?",
        "Domanda", JOptionPane.YES_NO_OPTION);
    return (yn == JOptionPane.YES_OPTION);
  }

  // Simulazione e grafica
  public static void visualizzaSimulazione(Simulazione simulazione) {
    TargetSimul target = new TargetSimul(simulazione);
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
    FinestraSimulazione finestra = target.finestra;
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
  
  private static class TargetSimulazione implements Runnable {

    private FinestraDatiSimulazione finestra;

    @Override
    public void run() {
      finestra = new FinestraDatiSimulazione();
    }
  }
  
  private static class TargetSemaforo implements Runnable {

    private FinestraDatiSemaforo finestra;

    @Override
    public void run() {
      finestra = new FinestraDatiSemaforo();
    }
  }
  

  private static class TargetSimul implements Runnable {

    private FinestraSimulazione finestra;
    private Simulazione simulazione;

    public TargetSimul(Simulazione s) {
      simulazione = s;
    }

    @Override
    public void run() {
      finestra = new FinestraSimulazione(simulazione);
    }
  }

}
