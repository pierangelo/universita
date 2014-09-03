package app.gui;

import java.awt.BorderLayout;
import java.awt.Font;
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

import app.dominio.Cliente;
import app.dominio.EccezioneMoltMinMax;
import app.dominio.Fattura;
import app.dominio.Ordine;
import app.dominio.Prodotto;
import app.dominio.TipoLinkContiene;

public class FinestraFattura extends JFrame implements ActionListener {

  public FinestraFattura(Fattura fattura) {
    super("Fattura");
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    JTextArea areaTesto = new JTextArea(30, 50);
    areaTesto.setEditable(false);
    areaTesto.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

    JScrollPane scroll = new JScrollPane(areaTesto);

    try {
      Ordine ordine = fattura.getLinkRelativaA().getOrdine();
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
      areaTesto.append("PIZZERIA \"AL LABORATORIO\"\n\nFattura: " + fattura.getCodice() + " relativa all'ordine " + ordine.getId() + " del " + sdf.format(ordine.getData()) + "\n\n");
      Cliente cliente = ordine.getLinkEseguitoDa();
      areaTesto.append("Cliente: " + cliente.getNome() + " " + cliente.getCognome() + "\n\n\n");
      areaTesto.append("Prodotti:\n\n");
      Set<TipoLinkContiene> prodotti = ordine.getLinkContiene();
      Iterator<TipoLinkContiene> it = prodotti.iterator();
      TipoLinkContiene link;
      Prodotto prodotto;
      double subTot;
      while(it.hasNext()) {
        link = it.next();
        prodotto = link.getProdotto();
        subTot = link.getQuantita() * link.getProdotto().getPrezzo(); 
        areaTesto.append(prodotto.getNome() + "\n\t" + link.getQuantita() + " x " + prodotto.getPrezzo() + " = " + subTot + "\n");
      }
      areaTesto.append("\n-----------------------\nTOTALE: " + ordine.totale() + " Euro");
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
