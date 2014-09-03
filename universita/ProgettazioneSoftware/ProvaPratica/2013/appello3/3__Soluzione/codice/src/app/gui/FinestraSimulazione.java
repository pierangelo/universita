package app.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app._gestioneeventi.Environment;
import app._gestioneeventi.EsecuzioneEnvironment;
import app.dominio.EccezioneMoltMinMax;
import app.dominio.Simulazione;
import app.dominio.TipoLinkComprende;
import app.dominio.eventi.Accendi;
import app.dominio.eventi.Spegni;
import app.dominio.eventi.Verde;

public class FinestraSimulazione extends JFrame implements ActionListener {

  private Simulazione simulazione;
  private final JButton bottoneAccendi = new JButton("Accendi");
  private final JButton bottoneAvvia = new JButton("Avvia");
  private final JButton bottoneSpegni = new JButton("Spegni");
  private final JButton bottoneChiudi = new JButton("Chiudi");

  public FinestraSimulazione(Simulazione sim) {
    super("Simulazione");
    this.simulazione = sim;

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    try {
      List<TipoLinkComprende> links = simulazione.getLinkComprende();
      JPanel mainPanel = new JPanel();
      mainPanel.setLayout(new GridLayout(1, links.size()));
      for (TipoLinkComprende link : links) {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel semPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        semPanel.add(new SemaforoView(link.getSemaforo()));
        panel.add(semPanel);
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelPanel.add(new JLabel(link.getSemaforo().getNome()));
        panel.add(labelPanel, BorderLayout.PAGE_END);
        mainPanel.add(panel);
      }
      this.getContentPane().add(mainPanel);

      JPanel buttonsPanel = new JPanel();
      bottoneAccendi.setActionCommand("ON");
      bottoneAccendi.addActionListener(this);
      bottoneChiudi.setActionCommand("CLOSE");
      bottoneChiudi.addActionListener(this);
      bottoneAvvia.setActionCommand("GO");
      bottoneAvvia.addActionListener(this);
      bottoneSpegni.setActionCommand("OFF");
      bottoneSpegni.addActionListener(this);

      bottoneAvvia.setEnabled(false);
      bottoneSpegni.setEnabled(false);


      buttonsPanel.add(bottoneAccendi);
      buttonsPanel.add(bottoneAvvia);
      buttonsPanel.add(bottoneSpegni);
      buttonsPanel.add(bottoneChiudi);

      this.getContentPane().add(buttonsPanel, BorderLayout.PAGE_END);

    } catch (EccezioneMoltMinMax e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    pack();
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
    // TODO Auto-generated method stub
    String command = e.getActionCommand();

    if (command.equals("ON")) {
      Environment.aggiungiEvento(new Accendi(null, null));
      bottoneAvvia.setEnabled(true);
      bottoneAccendi.setEnabled(false);
    }
    else if (command.equals("GO")) {
      try {
        Environment.aggiungiEvento(new Verde(null, simulazione.getLinkComprende().get(0).getSemaforo(),simulazione.getLinkComprende().get(0).getSemaforo().getDurataVerde()));
        bottoneAvvia.setEnabled(false);
        bottoneSpegni.setEnabled(true);
      } catch (EccezioneMoltMinMax e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
    else if (command.equals("OFF")) {
      Environment.aggiungiEvento(new Spegni(null, null));
      bottoneSpegni.setEnabled(false);
      bottoneAccendi.setEnabled(true);
    }
    else { //spegni
      this.dispose();
      EsecuzioneEnvironment.disattivaListener();
      synchronized (this.getContentPane()) {
        this.getContentPane().notify();
      }
    }
  }

}
