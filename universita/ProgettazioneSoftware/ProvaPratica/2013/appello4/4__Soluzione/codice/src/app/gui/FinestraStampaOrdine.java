package app.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import app.dominio.Tavolo;
import app.dominio.EccezioneMoltMinMax;
import app.dominio.Ordine;
import app.dominio.Prodotto;
import app.dominio.TipoLinkContiene;

public class FinestraStampaOrdine extends JFrame implements ActionListener {

  public FinestraStampaOrdine(Ordine ordine) {
    super("Ordine");
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    JTextArea areaTesto = new JTextArea(30, 50);
    areaTesto.setEditable(false);

    JScrollPane scroll = new JScrollPane(areaTesto);

    try {
      //Ordine ordine = fattura.getLinkRelativaA().getOrdine();
      SimpleDateFormat sdf = new SimpleDateFormat("H:mm dd/MM/yyyy", Locale.ITALIAN);
      areaTesto.append("PUB \"AL LABORATORIO\"\n\nOrdine " + ordine.getId() + " -- " + sdf.format(ordine.getData())+"\n");

      Tavolo tavolo = ordine.getLinkRelativoA();
      areaTesto.append(tavolo + "\n\n\n");
      areaTesto.append("Prodotti:\n\n");
      Set<TipoLinkContiene> prodotti = ordine.getLinkContiene();
      Iterator<TipoLinkContiene> it = prodotti.iterator();
      TipoLinkContiene link;
      Prodotto prodotto;
      while(it.hasNext()) {
        link = it.next();
        prodotto = link.getProdotto(); 
        areaTesto.append(prodotto.getNome() + "\t" + link.getQuantita() + "\n");
      }
    }
    catch(EccezioneMoltMinMax e) {
      e.printStackTrace();
      System.exit(1);
    }

    JPanel pannelloInferiore = new JPanel();
    JButton continua = new JButton("Avanti");
    continua.addActionListener(this);
    pannelloInferiore.add(continua);

    getContentPane().add(scroll, BorderLayout.CENTER);
    getContentPane().add(pannelloInferiore, BorderLayout.PAGE_END);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
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
  public void actionPerformed(ActionEvent e) {
    this.dispose();
    synchronized (getContentPane()) {
      getContentPane().notify();
    }
  }

}
