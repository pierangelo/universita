package app.attivita;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import app.dominio.*;
import app.gui.*;

public class AttivitaIO {
  
  private AttivitaIO() {}

  
  // Dati Regata
  public static Regata inserisciDatiRegata() {
    
    TargetDatiRegata target = new TargetDatiRegata();
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
    FinestraDatiRegata finestra = target.finestra;
    finestra.aspettaOK();
    return new Regata(finestra.leggiNome(), finestra.leggiDistanza());
    
  }

  // Dati Equipaggio
  public static Equipaggio inserisciDatiEquipaggio() {
    String nome;
    do {
      nome = JOptionPane.showInputDialog(null,
          "Specificare il nome dell'equipaggio:", "Iscrizione Equipaggio",
          JOptionPane.PLAIN_MESSAGE);
    } while (nome == null || nome.trim().equals(""));

    return (new Equipaggio(nome.trim()));
  }

  
  // Altro Equipaggio
  public static boolean chiediSeAltroEquipaggio() {
    int yn = JOptionPane.showConfirmDialog(null,
        "Si vuole iscrivere un altro equipaggio alla regata?",
        "Iscrizione Equipaggio", JOptionPane.YES_NO_OPTION);
    return (yn == JOptionPane.YES_OPTION);
  }

  
  // Riassunto Regata
  public static void visualizzaRiassuntoRegata(Regata regata) {
    
    TargetRiassunto target = new TargetRiassunto(regata);
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
    FinestraRiassuntoRegata finestra = target.finestra;
    finestra.aspettaOK();

  }

  // Visualizza Regata
  public static void visualizzaRegata(Regata regata) {
    
    TargetSimul target = new TargetSimul(regata);
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
    RegataView finestra = target.finestra;
    finestra.aspettaOK();
  }
  
  
  private static class TargetDatiRegata implements Runnable {

    private FinestraDatiRegata finestra;

    @Override
    public void run() {
      finestra = new FinestraDatiRegata();
    }
  }
  
  private static class TargetRiassunto implements Runnable {

    private FinestraRiassuntoRegata finestra;
    private Regata regata;

    private TargetRiassunto(Regata regata) {
      this.regata = regata;
    }
    
    @Override
    public void run() {
      
      Set<Equipaggio> vincitori = null;
      Set<TipoLinkPartecipa> partecipanti = null;
      try {
        vincitori = regata.getLinkVincitori();
        partecipanti = regata.getLinkPartecipa();
        finestra = new FinestraRiassuntoRegata(vincitori, partecipanti);
      } catch (EccezioneMoltMinMax e) {
        e.printStackTrace();
        System.exit(1);
      } catch (EccezioneSubset e) {
        e.printStackTrace();
        System.exit(1);
      }
      
      
    }
  }
  
  private static class TargetSimul implements Runnable {

    private RegataView finestra;
    private Regata regata;
    
    private TargetSimul(Regata reg) {
      regata = reg;
    }

    @Override
    public void run() {
      finestra = new RegataView(regata);
    }
  }
  
  

}
