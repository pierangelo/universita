package app.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;


import app.dominio.*;

public class FinestraRiassuntoRegata extends JFrame implements ActionListener {

  private JList listaVincitori;
  private JTable tabellaEquipaggi;
  private JTabbedPane tabPnl = new JTabbedPane();
  
  private final JPanel sudPnl = new JPanel();
  private final JButton okBtn = new JButton("OK");

  public FinestraRiassuntoRegata(Set<Equipaggio> vincitori, Set<TipoLinkPartecipa> linkPartecipanti) {
   
    super("Riassunto della regata e vincitori");
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    listaVincitori = popolaListaVincitori(vincitori);
    tabellaEquipaggi = popolaTabellaClassifica(linkPartecipanti);
    
    tabPnl.add("Tabella degli Equipaggi", new JScrollPane(tabellaEquipaggi));
    tabPnl.add("Lista dei Vincitori", new JScrollPane(listaVincitori));
    
    okBtn.addActionListener(this);
    sudPnl.add(okBtn);
    
    getContentPane().add(tabPnl, BorderLayout.CENTER);
    getContentPane().add(sudPnl, BorderLayout.PAGE_END);
    
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  private JTable popolaTabellaClassifica(Set<TipoLinkPartecipa> linkPartecipanti) {
    String[][] tabella = new String[linkPartecipanti.size()][2];
    int riga = 0;
    for (TipoLinkPartecipa l : linkPartecipanti) {
      tabella[riga][0] = l.getEquipaggio().getNome();
      tabella[riga++][1] = l.getKmPercorsi() + "";
    }
    String[] intestazioni = { "Nome Equipaggio", "Km percorsi" };
    return new JTable(tabella, intestazioni);
  }

  private JList popolaListaVincitori(Set<Equipaggio> vincitori) {
    Vector<String> nomeVincitori = new Vector<String>();
    for (Equipaggio vincitore : vincitori) {
      nomeVincitori.add(vincitore.getNome());
    }
    return new JList(nomeVincitori);
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

  @Override
  public void actionPerformed(ActionEvent arg0) {
    // TODO Auto-generated method stub
    dispose();
    synchronized (getContentPane()) {
      getContentPane().notify();
    }
  }

}
