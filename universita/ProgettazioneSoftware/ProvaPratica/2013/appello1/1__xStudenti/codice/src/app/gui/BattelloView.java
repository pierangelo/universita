package app.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app._gestioneeventi.Environment;
import app.dominio.Tratta;
import app.dominio.Battello;
import app.dominio.Battello.Stato;
import app.dominio.eventi.Partenza;

public class BattelloView extends JFrame implements Observer, ActionListener {

  private Container frmContentPane;
  private PercorsoBattello percorso;
  private final JButton bottonePartenza = new JButton("Partenza");
  private final JButton bottoneChiudi = new JButton("Chiudi");
  private final Battello battello;
  private final Tratta tratta;

  public BattelloView(Battello t) {
    super(t.getLinkPercorre().getTratta().getNome());
    this.battello = t;
    this.tratta = t.getLinkPercorre().getTratta();
    
    // si registra come observer dell'oggetto di dominio che reppresenta il treno (model)
    t.addObserver(this);
    
    prepara();
  }

  private void prepara() {// prepara la finestra per la visualizzazione
    
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    frmContentPane = getContentPane();
    percorso = new PercorsoBattello(tratta);
    frmContentPane.add(percorso, BorderLayout.CENTER);
    JLabel infoTratta = new JLabel("Tratta: " + tratta.getNome() + " - Lunghezza: " + tratta.lunghezza() + " mt --- Battello: " + battello.getNome() + " - Lunghezza: " + battello.getLunghezza() + " mt");
    JPanel trattaInfo = new JPanel();
    trattaInfo.add(infoTratta);
    frmContentPane.add(trattaInfo, BorderLayout.PAGE_START);
    
    // Bottoni
    JPanel buttonPanel = new JPanel();
    // Partenza
    bottonePartenza.setActionCommand("parti");
    bottonePartenza.addActionListener(this);
    buttonPanel.add(bottonePartenza);
    // Chiudi
    bottoneChiudi.addActionListener(new ListenerBattelloView(this));
    bottoneChiudi.setEnabled(false);
    buttonPanel.add(bottoneChiudi);
    // Emergenza
    frmContentPane.add(buttonPanel, BorderLayout.PAGE_END);
    
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }
  
  @Override
  public void update(Observable treno, Object update) {
    
    Battello trenoUpd = (Battello)treno;
    
    if (update != null) { // cambio di posizione
      percorso.setPosizioneBattello(trenoUpd, ((Integer)update).intValue());
    }
    else { // cambio di stato
      if (trenoUpd.getStato() == Stato.ATTRACCATO) {
        bottonePartenza.setEnabled(true);
      }
      else if (trenoUpd.getStato() == Stato.IN_NAVIGAZIONE) {
        bottonePartenza.setEnabled(false);
      }
      else if (trenoUpd.getStato() == Stato.ARRIVATO) {
        bottoneChiudi.setEnabled(true);
        bottonePartenza.setEnabled(false);
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    if (command.equals("parti")) {
      Environment.aggiungiEvento(new Partenza(null, battello));
    }
  }
  
  public void aspettaOK() {
    synchronized (getContentPane()) {
      try {
        getContentPane().wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
        System.exit(1);
      }
    }
  }
}