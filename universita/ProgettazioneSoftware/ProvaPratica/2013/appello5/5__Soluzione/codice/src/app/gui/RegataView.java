package app.gui;

import javax.swing.*;

import app._gestioneeventi.Environment;
import app._gestioneeventi.EsecuzioneEnvironment;
import app.dominio.*;
import app.dominio.eventi.Start;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class RegataView extends JFrame implements Observer, ActionListener {

  
  private final JButton bottonePartenza = new JButton("Partenza");
  private final JButton bottoneChiudi = new JButton("Chiudi");
  
  protected Percorso percorso; // rendere accessibile all'attivita' di
                               // aggiornamento
  private Regata regata;

  public RegataView(Regata regata) {
    
    super(regata.getNome());
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    this.regata = regata;
    
    // si registra come observer degli equipaggi
    try {
      for(TipoLinkPartecipa link : this.regata.getLinkPartecipa()) {
        link.getEquipaggio().addObserver(this);
      }
    } catch (EccezioneMoltMinMax e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    prepara();
    
  }

  private void prepara() {// prepara la finestra per la visualizzazione
    
    percorso = new Percorso(regata);
    getContentPane().add(percorso, BorderLayout.CENTER);
    JLabel infoRegata = new JLabel("Regata: " + regata.getNome() + " - Km: " + regata.getDistanza());
    JPanel regataInfo = new JPanel();
    
    regataInfo.add(infoRegata);
    getContentPane().add(regataInfo, BorderLayout.PAGE_START);
    
    // Bottoni
    JPanel buttonPanel = new JPanel();
    // Partenza
    bottonePartenza.setActionCommand("parti");
    bottonePartenza.addActionListener(this);
    buttonPanel.add(bottonePartenza);
    // Chiudi
    bottoneChiudi.addActionListener(this);
    bottoneChiudi.setEnabled(false);
    buttonPanel.add(bottoneChiudi);

    getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
    
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }
  
  @Override
  public void update(Observable equipaggio, Object update) {
    //invocato in seguito ad un aggiornamento in un equipaggio
    
    Equipaggio equi = (Equipaggio)equipaggio;
    
    if (update != null) {
      percorso.setPosizioneEquipaggio(equi, (Float)update);
    }
    else { // cambio di stato
      if (equi.getStato() == app.dominio.Equipaggio.Stato.FINITO) {
        bottoneChiudi.setEnabled(true);
      }
    }
    
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    String command = e.getActionCommand();
    if (command.equals("parti")) {
      bottonePartenza.setEnabled(false);
      Environment.aggiungiEvento(new Start(null, null));
    }
    else { // chiudi
      dispose();
      EsecuzioneEnvironment.disattivaListener();
      synchronized (getContentPane()) {
        getContentPane().notify();
      }
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
